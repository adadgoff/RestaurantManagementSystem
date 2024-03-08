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
    description      varchar(1023),
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
    text         varchar(2047)                not null,
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
    add constraint FKedtd63h4lhqsm05mqcmrclpy9 foreign key (dish_id) references dishes;
alter table if exists order_cooked_dishes
    add constraint FKcilv664617gcc86np1igbmk8f foreign key (dish_id) references dishes;
alter table if exists order_cooked_dishes
    add constraint FKpys9l5jaitj60t79ketdncnt foreign key (order_id) references orders;
alter table if exists order_cooking_dishes
    add constraint FKq0xgkx97bvj4aqdju8uium85m foreign key (dish_id) references dishes;
alter table if exists order_cooking_dishes
    add constraint FKqeiyt24kdjuwyqwj4og9gp18w foreign key (order_id) references orders;
alter table if exists order_waiting_dishes
    add constraint FK60yddx5lpadav7mjokbcqpbtn foreign key (dish_id) references dishes;
alter table if exists order_waiting_dishes
    add constraint FK7mhv2oadrr1jxx6rstu1kmakb foreign key (order_id) references orders;
alter table if exists orders
    add constraint FK8n3xo6819cyjvfd4osuatshjl foreign key (user_uuid) references users;
alter table if exists reviews
    add constraint FK4d7oru9qv44238vvop3j6rwx0 foreign key (dish_id) references dishes;
alter table if exists reviews
    add constraint FKbof7gw1n3p721i4j1vqj7ycq9 foreign key (user_uuid) references users;
alter table if exists user_roles
    add constraint FKb4bms60ebskkrd05297us35x9 foreign key (user_uuid) references users;
alter table if exists users
    add constraint FK17herqt2to4hyl5q5r5ogbxk9 foreign key (image_id) references images;