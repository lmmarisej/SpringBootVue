-- auto-generated definition
create table book
(
    id                 int auto_increment
        primary key,
    create_by          varchar(255) null,
    create_time        datetime     null,
    last_modified_by   varchar(255) null,
    last_modified_time datetime     null,
    author             varchar(255) null,
    name               varchar(255) null,
    price              float        null
);

