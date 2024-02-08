create table couriers
(
    user_id    bigint primary key not null,
    status     varchar(255),
    updated_at timestamp          not null default now()
)