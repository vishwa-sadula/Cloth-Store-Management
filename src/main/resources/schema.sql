create database dbms_proj;
use dbms_proj;

create table users
(
	user_id int primary key auto_increment,
    user_fname varchar(20),
    user_lname varchar(20),
    username varchar(20) ,
    user_email varchar(30),
    user_password varchar(100),
    user_type varchar(10) default "USER",
    city varchar(20),
    street varchar(20),
    pin int
    
);

create table employees
(
	emp_id int primary key auto_increment,
    emp_fname varchar(20),
    emp_lname varchar(20),
    emp_email varchar(30),
    emp_gender char,
    emp_join_date date
    
);

create table brands
(
	brand_id int primary key auto_increment,
	brand_name varchar(15),
    brand_email varchar(30)
);

create table categories
(
	cat_id int primary key auto_increment,
    cat_name varchar(15)
);

create table products
(
	prod_id int primary key auto_increment,
    prod_title varchar(30),
    prod_description text,
    prod_colour varchar(15),
    cat_id int ,
    foreign key (cat_id) references categories(cat_id) on update cascade on delete restrict,
    brand_id int ,
    foreign key (brand_id) references brands(brand_id) on update cascade on delete restrict,
    price int,
    size varchar(10),
    quantity int,
    image_path text 
    
);


create table cart_details
(
	user_id int ,
    foreign key (user_id) references users(user_id) on update cascade on delete restrict ,
    prod_id int,
    foreign key (prod_id) references products(prod_id) on update cascade on delete restrict,
    primary key (user_id, prod_id),
    prod_quantity int default 1 
);

create table orders
(
	order_id int primary key auto_increment,
    user_id int ,
    foreign key (user_id) references users(user_id) ,
    emp_id int default 1,
    foreign key (emp_id) references employees(emp_id) on update cascade on delete restrict,
    order_status varchar(30) default "Order Placed"
    
);

update users set user_type='ADMIN' where username="";
insert into employees values(1,"NOT ASSIGNED","-","-","-",'0000-00-00');
