create table users_roles
(
    user_id bigserial not null,
    role_id bigserial not null,
    primary key (user_id,role_id)
);