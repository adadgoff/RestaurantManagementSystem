create sequence dishes_seq start with 1 increment by 50;
create sequence images_seq start with 1 increment by 50;
create sequence orders_seq start with 1 increment by 50;
create sequence reviews_seq start with 1 increment by 50;

create table dishes
(
    active           boolean        not null,
    cooking_time     numeric(21, 0) not null,
    "weight (grams)" integer        not null,
    count            bigint         not null,
    id               bigint         not null,
    "price (rubles)" bigint         not null,
    description      varchar(1024),
    name             varchar(255)   not null unique,
    primary key (id)
);
create table images
(
    dish_id      bigint,
    id           bigint       not null,
    content_type varchar(255) not null,
    img_binary   oid          not null,
    primary key (id)
);
create table order_cooked_dishes
(
    dish_id  bigint not null,
    order_id bigint not null
);
create table order_cooking_dishes
(
    dish_id  bigint not null,
    order_id bigint not null
);
create table order_waiting_dishes
(
    dish_id  bigint not null,
    order_id bigint not null
);
create table orders
(
    paid            boolean                     not null,
    "cost (rubles)" bigint                      not null,
    end_time        timestamp(6) with time zone,
    id              bigint                      not null,
    start_time      timestamp(6) with time zone not null,
    user_uuid       uuid,
    status          varchar(255)                not null check (status in ('COOKING', 'COOKED', 'CANCELED')),
    primary key (id)
);
create table reviews
(
    rating       smallint                    not null check (rating between 0 and 4),
    dish_id      bigint,
    id           bigint                      not null,
    publish_time timestamp(6) with time zone not null,
    user_uuid    uuid,
    text         varchar(2048)               not null,
    primary key (id)
);
create table user_roles
(
    user_uuid uuid not null,
    roles     varchar(255) check (roles in ('ROLE_USER', 'ROLE_ADMIN'))
);
create table users
(
    active   boolean      not null,
    image_id bigint unique,
    uuid     uuid         not null,
    email    varchar(255) not null unique,
    name     varchar(255) not null,
    password varchar(255) not null,
    primary key (uuid)
);

alter table if exists images
    add constraint FK_images_dish foreign key (dish_id) references dishes;
alter table if exists order_cooked_dishes
    add constraint FK_order_cooked_dishes_dish foreign key (dish_id) references dishes;
alter table if exists order_cooked_dishes
    add constraint FK_order_cooked_dishes_order foreign key (order_id) references orders;
alter table if exists order_cooking_dishes
    add constraint FK_order_cooking_dishes_dish foreign key (dish_id) references dishes;
alter table if exists order_cooking_dishes
    add constraint FK_order_cooking_dishes_order foreign key (order_id) references orders;
alter table if exists order_waiting_dishes
    add constraint FK_order_waiting_dishes_dish foreign key (dish_id) references dishes;
alter table if exists order_waiting_dishes
    add constraint FK_order_waiting_dishes_order foreign key (order_id) references orders;
alter table if exists orders
    add constraint FK_orders_user foreign key (user_uuid) references users;
alter table if exists reviews
    add constraint FK_reviews_dish foreign key (dish_id) references dishes;
alter table if exists reviews
    add constraint FK_reviews_user foreign key (user_uuid) references users;
alter table if exists user_roles
    add constraint FK_user_roles_user foreign key (user_uuid) references users;
alter table if exists users
    add constraint FK_users_image foreign key (image_id) references images;
