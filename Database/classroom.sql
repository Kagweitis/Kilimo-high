create table classroom
(
    id            bigint auto_increment
        primary key,
    class_name    varchar(255) not null,
    class_teacher varchar(255) null,
    deleted       bit          null,
    stream_name   varchar(255) not null
);