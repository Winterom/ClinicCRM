create table app_user
(
    id         bigserial primary key,
    firstname   varchar(50) not null,
    surname   varchar(50) not null,
    lastname    varchar (50),
    password   varchar(255) not null,
    email      varchar(50) unique not null,
    phone_number  varchar (25) unique,
    status     varchar(30) not null,
    email_verified boolean not null ,
    telephone_verified boolean not null,
    expired_credentials timestamptz not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    roles bigserial
);

ALTER TABLE app_user ALTER COLUMN email_verified SET DEFAULT FALSE;
ALTER TABLE app_user ALTER COLUMN telephone_verified SET DEFAULT FALSE;
