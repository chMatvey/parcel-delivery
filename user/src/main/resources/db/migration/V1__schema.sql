create sequence users_id_seq;

create table users
(
    id         bigint       not null primary key default nextval('users_id_seq'),
    login      varchar(20)  not null unique,
    password   varchar(255) not null,
    role       varchar(10)  not null,
    created_at timestamp    not null             default now()
)