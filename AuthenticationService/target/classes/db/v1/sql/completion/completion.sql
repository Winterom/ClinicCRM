insert into app_user (firstname,surname,lastname, password, email, phone_number, expired_credentials) VALUES
    ('Алексей','Гриценко','Сергеевич','$2y$10$QbS3JsL7OlgSN0t.zhRJbuj.uqpID/yusXDmsACGFNKzPdaheJShC','pumpum@mail.ru','+7-921-951-66-33',current_timestamp+interval '140 hour'),
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
    ('ADMIN_USER_READ','Описание привелегии'),
    ('ADMIN_USER_WRITE','Описание привелегии'),
    ('ADMIN_USER_DELETE','Описание привелегии'),
    ('REGISTRATION_READ','Описание привелегии'),
    ('REGISTRATION_WRITE','Описание привелегии');



insert into roles_authorities ( role_id,authority_id) VALUES
    (1,1),
    (1,2),
    (1,3),
    (2,4),
    (2,5);