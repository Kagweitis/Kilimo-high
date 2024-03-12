create table stream
(
    id          bigint auto_increment
        primary key,
    deleted     bit          null,
    stream_id   varchar(255) null,
    stream_name varchar(255) not null
);

