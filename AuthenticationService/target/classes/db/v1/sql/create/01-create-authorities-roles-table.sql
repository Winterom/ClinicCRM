create table roles_authorities
(
    authority_id bigserial not null,
    role_id bigserial not null,
    primary key (role_id, authority_id)
);