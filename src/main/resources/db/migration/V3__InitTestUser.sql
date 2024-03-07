insert into users (active, email, name, password, image_id, uuid)
values (true, 'test@test.test', 'test', '$2a$11$RDVKJif5Zmo5oaDrsRkpW.9.ZKRpa9q6TVmcgtqF8Y.LoDtoUaD9G', null,
        'a9201ac1-9a8b-4dc4-a253-049ab05e6901'::uuid);

insert into user_roles (user_uuid, roles)
values ('a9201ac1-9a8b-4dc4-a253-049ab05e6901'::uuid, 'ROLE_USER');
