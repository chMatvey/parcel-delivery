create sequence orders_id_seq;

create table orders
(
    id          bigint       not null primary key default nextval('orders_id_seq'),
    user_id     bigint       not null,
    courier_id  bigint,
    status      varchar(20)  not null,
    details     varchar(255) not null,
    origin      varchar(255) not null,
    destination varchar(255) not null,
    version     integer      not null,
    created_at  timestamp    not null             default now(),
    updated_at  timestamp    not null             default now()
);

create index orders_user_id_idx on orders (user_id);

create index orders_status_idx on orders (status);