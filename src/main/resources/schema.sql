drop table if exists demo;
create table if not exists demo
(
    id int primary key auto_increment not null,
    name varchar (100) default null,
    exist boolean not null default false
);
