create table refresh_token_storage
(
    id          bigserial primary key,
    app_user_id bigserial,
    token       varchar(50),
    expired_date timestamp not null,
    created_at timestamp default current_timestamp
)