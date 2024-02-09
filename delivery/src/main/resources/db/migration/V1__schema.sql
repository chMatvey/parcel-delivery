create table deliveries
(
    order_id         bigint primary key not null,
    client_id        bigint             not null,
    courier_id       bigint             not null,

    status           varchar(20)        not null,
    completed        boolean            not null default false,
    canceled         boolean            not null default false,

    source_address   varchar(255)       not null,
    delivery_address varchar(255)       not null,

    delivery_date    timestamp,

    version          integer            not null,
    created_at       timestamp          not null default now(),
    updated_at       timestamp          not null default now()
);

create index deliveries_courier_id_order_id_idx on deliveries (courier_id, order_id)
    where completed = false and canceled = false;

create index deliveries_order_idx on deliveries (order_id);