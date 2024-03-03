create sequence images_seq start with 1 increment by 50;
create sequence orders_seq start with 1 increment by 50;
create sequence reviews_seq start with 1 increment by 50;

---------------------------------------------------------------------

create table dishes
(
    cooking_time     numeric(21, 0) not null,
    "weight (grams)" integer,
    count            bigint         not null,
    id               bigint         not null,
    order_id         bigint,
    "price (rubles)" bigint         not null,
    description      varchar(255),
    name             varchar(255)   not null,
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

create table orders
(
    status          smallint check (status between 0 and 2),
    "cost (rubles)" bigint,
    end_time        timestamp(6) with time zone,
    id              bigint not null,
    start_time      timestamp(6) with time zone,
    user_id         uuid,
    primary key (id)
);

create table reviews
(
    dish_id      bigint                      not null,
    id           bigint                      not null,
    publish_time timestamp(6) with time zone not null,
    user_id      uuid                        not null,
    text         varchar(255)                not null,
    primary key (id)
);

create table user_roles
(
    user_id uuid not null,
    roles   varchar(255) check (roles in ('ROLE_USER', 'ROLE_ADMIN'))
);

create table users
(
    active   boolean      not null,
    image_id bigint unique,
    uuid     uuid         not null,
    email    varchar(255) not null unique,
    name     varchar(255),
    password varchar(255) not null,
    primary key (uuid)
);

---------------------------------------------------------------------

alter table if exists dishes
    add constraint FK_dishes_orders foreign key (order_id) references orders;

alter table if exists images
    add constraint FK_images_dishes foreign key (dish_id) references dishes;

alter table if exists orders
    add constraint FK_orders_users foreign key (user_id) references users;

alter table if exists reviews
    add constraint FK_reviews_dishes foreign key (dish_id) references dishes;

alter table if exists reviews
    add constraint FK_reviews_users foreign key (user_id) references users;

alter table if exists user_roles
    add constraint FK_user_roles_users foreign key (user_id) references users;

alter table if exists users
    add constraint FK_users_images foreign key (image_id) references images;
