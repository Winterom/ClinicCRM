create table app_role
(
    id      bigserial primary key,
    role_name varchar(50) unique not null ,
    description text not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    locked      boolean default false

);