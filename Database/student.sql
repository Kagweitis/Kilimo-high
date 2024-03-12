create table student
(
    id           bigint auto_increment
        primary key,
    admission_no varchar(255) null,
    class_name   varchar(255) not null,
    deleted      bit          null,
    first_name   varchar(255) null,
    last_name    varchar(255) null,
    stream_name  varchar(255) not null
);