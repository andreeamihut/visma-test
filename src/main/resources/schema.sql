create table customer
(
   id integer not null,
   name varchar(255) not null,
   primary key(id)
);

create table product
(
   id integer not null,
   name varchar(255) not null,
   price integer not null,
   primary key(id)
);

create table rebate
(
   id integer not null,
   type varchar(255) not null,
   customer_id integer not null,
   primary key(id),
   foreign key (customer_id) references customer(id)
);