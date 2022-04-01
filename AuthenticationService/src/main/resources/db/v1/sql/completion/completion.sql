insert into app_user (firstname,surname,lastname, password, email, phone_number, expired_credentials,status) VALUES
    ('Алексей','Гриценко','Сергеевич','$2y$10$QbS3JsL7OlgSN0t.zhRJbuj.uqpID/yusXDmsACGFNKzPdaheJShC','pumpum@mail.ru','+7-921-951-66-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Владимир','Васильев','Анатольевич','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum2@mail.ru','+7-921-951-77-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Оксана','Егорова','Андреевна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum3@mail.ru','+7-921-951-74-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Елена','Белова','Владимировна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum4@mail.ru','+7-921-951-75-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Антон','Иванов','Иванович','$2y$10$QbS3JsL7OlgSN0t.zhRJbuj.uqpID/yusXDmsACGFNKzPdaheJShC','pumpum12@mail.ru','+7-921-951-67-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Михаил','Михайлов','Анатольевич','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum5@mail.ru','+7-921-951-78-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Юлия','Беглова','Романовна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum6@mail.ru','+7-921-951-79-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Валерия','Мясоедова','Максимовна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum7@mail.ru','+7-921-951-80-33',current_timestamp+interval '140 hour','ACTIVE'),
    ('Максим','Максимов','Сергеевич','$2y$10$QbS3JsL7OlgSN0t.zhRJbuj.uqpID/yusXDmsACGFNKzPdaheJShC','pumpum8@mail.ru','+7-921-951-66-35',current_timestamp+interval '140 hour','DELETED'),
    ('Роман','Романов','Анатольевич','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum9@mail.ru','+7-921-951-77-36',current_timestamp+interval '140 hour','ACTIVE'),
    ('Ксения','Онегина','Егоровна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum10@mail.ru','+7-921-951-74-37',current_timestamp+interval '140 hour','ACTIVE'),
    ('Катерина','Юрьева','Дмитриевна','$2y$10$0f31UdYHIDYEdhBdOIyDIeRsgJycl5Gw4lDoWYFtUvEueBBokVTZO','pumpum11@mail.ru','+7-921-951-74-39',current_timestamp+interval '140 hour','ACTIVE');
insert into app_role (role_name, description) values
    ('ROLE_ADMIN','Роль администратора'),
    ('ROLE_DOCTOR', 'Роль врача'),
    ('ROLE_REGISTRANT', 'Роль работника регистратуры');                                              ;


insert into users_roles (user_id, role_id) VALUES
    (1,1),
    (2,1),
    (3,3),
    (4,3),
    (5,3),
    (6,2),
    (7,2),
    (8,2),
    (9,2),
    (10,2),
    (11,2);
insert into authorities (authority,role) values
    ('ADMIN_USER_READ',1),
    ('ADMIN_USER_WRITE',1),
    ('ADMIN_USER_DELETE',1),
    ('REGISTRATION_READ',3),
    ('REGISTRATION_WRITE',3);



