insert into users (active, email, name, password, image_id, uuid)
values (true, 'admin@admin.admin', 'admin', '$2a$11$klxFUPGpxQzWAL4AeDW3behzzyObBooQ7OsXyafZms68IIfn6XHca',
        null, '27c71dd1-4021-417c-97d7-6e6cda4dba6b'::uuid);

insert into user_roles (user_uuid, roles)
values ((select uuid from users where email = 'admin@admin.admin'), 'ROLE_USER');
