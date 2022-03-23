create table authorities
(
    id      bigserial primary key,
    authority varchar(40) unique not null ,
    description text not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);