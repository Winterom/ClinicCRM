insert into app_user (firstname,surname,lastname, password, email, phone_number, expired_credentials) VALUES
    ('Алексей','Гриценко','Сергеевич','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum@mail.ru','+7-921-951-66-33',current_timestamp+interval '140 hour'),
    ('Владимир','Васильев','Анатольевич','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum2@mail.ru','+7-921-951-77-33',current_timestamp+interval '140 hour'),
    ('Оксана','Егорова','Андреевна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum3@mail.ru','+7-921-951-74-33',current_timestamp+interval '140 hour');

insert into app_role (role_name, description) values
    ('ROLE_ADMIN','Роль администратора'),
    ('ROLE_DOCTOR', 'Роль врача'),
    ('ROLE_REGISTRANT', 'Роль работника регистратуры'),
    ('ROLE_DEFAULT','Первичная роль при регистрации');                                                 ;


insert into users_roles (user_id, role_id) VALUES
    (1,1),
    (2,2),
    (3,3);

insert into authorities (authority,description) values
    ('READ_ALL','Описание привелегии'),
    ('UPDATE_ALL','Описание привелегии'),
    ('DELETE_ALL','Описание привелегии'),
    ('CREATE_ALL','Описание привелегии'),
    ('AUTHORITY_1','Описание привелегии'),
    ('AUTHORITY_2','Описание привелегии'),
    ('AUTHORITY_3','Описание привелегии'),
    ('AUTHORITY_4','Описание привелегии'),
    ('AUTHORITY_5','Описание привелегии'),
    ('AUTHORITY_6','Описание привелегии'),
    ('AUTHORITY_7','Описание привелегии'),
    ('AUTHORITY_8','Описание привелегии'),
    ('AUTHORITY_9','Описание привелегии');


insert into roles_authorities ( role_id,authority_id) VALUES
    (1,1),
    (1,2),
    (1,3),
    (1,4),
    (2,5),
    (2,6),
    (2,7),
    (2,8),
    (2,9),
    (3,10),
    (3,11),
    (3,12),
    (3,13);