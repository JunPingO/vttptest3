-- Creating eshop database
drop database if exists eshop;
create database eshop;

use eshop;

create table customers (
	name varchar(128) not null,
    address varchar(256) not null,
    email varchar(128) not null,
    primary key(name)
);

load data local infile "C:\portableWorkspace\vttptest3\eshop\database\data.csv" 
into table customers
fields terminated by ':'
lines terminated by '\n'
ignore 1 rows
(name, address, email)
;


-- creating orders

create table orders (
    order_id char(8) not null,
    name varchar(128) not null,
    order_date date not null,

    primary key(order_id)
);

create table line_item (
    item_id int auto_increment not null,
    description text not null,
    quantity int default '1',
    order_id char(8) not null,

    primary key(item_id),
    constraint fk_order_id
        foreign key(order_id) references orders(order_id)
);