ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles
        FOREIGN KEY (user_id)
            REFERENCES app_user (id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_roles_users
        FOREIGN KEY (role_id)
            REFERENCES app_role (id);

ALTER TABLE authorities
    ADD CONSTRAINT fk_roles_authorities
        FOREIGN KEY (role)
            REFERENCES app_role (id);


ALTER TABLE refresh_token_storage
    ADD CONSTRAINT fk_refresh_user
        FOREIGN KEY (app_user_id)
            REFERENCES app_user (id);