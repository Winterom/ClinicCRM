create table authorities
(
    id      bigserial primary key,
    authority varchar(40) unique not null ,
    role bigserial
);