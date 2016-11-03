/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jorgen
 * Created: 30.10.2016
 */

/* Initialize tables. Drop all existing tables. BE AWARE!*/

DROP DATABASE IF EXISTS lookforbook;

CREATE DATABASE lookforbook CHARACTER SET utf8 COLLATE utf8_general_ci;

USE lookforbook;

/* TABLES: */
/*book - book table for item*/
/*user - user table for user object*/
/*reviews - for all reviews of items*/
/*invoice - for user invoices (represents one bill) !is processed*/
/*invoiceitem - book id and it's amount (represent's line in a cart)*/
/*userPass - users auth data*/
/*userRole - users roles data*/

CREATE TABLE User (
    user_id int not null auto_increment,
    first_name varchar(50),
    last_name varchar(50),
    father_name varchar(50),
    email varchar(50),
    country varchar(50),
    district varchar(50),
    city varchar(50),
    address varchar(100),
    zip varchar(10),
    
    credit_card_type varchar(20),
    credit_card_number varchar(20),
    credit_card_exp varchar(7),

    PRIMARY KEY (user_id)
);

CREATE TABLE Book (
    book_id int not null auto_increment,
    book_title varchar(100) not null default '',
    book_desc varchar(1000) not null default '',
    book_price int not null default '0',
    
    book_isbn varchar(13) not null default '0000000000000',
    book_author varchar(50) not null default 'unknown',
    book_genre varchar(20) not null default 'Misc',
    book_year integer(4) not null default '0',
    book_lang varchar(10) not null default 'unknown',
    book_house varchar(20) not null default 'unknown',
    book_stock integer(4) not null default '0'

    PRIMARY KEY (book_id)
);

CREATE TABLE Invoice (
    invoice_id int not null auto_increment,
    user_id int not null,
    
    invoice_date datetime not null default CURRENT_TIMESTAMP,
    total_amount smallint not null default '0',
    is_processed enum('y','n') default null,
    delivery enum('y','n') default null,
    delivery_cost int default null, 

    PRIMARY KEY (invoice_id),
    FOREIGN KEY (user_id) REFERENCES User (user_id)
);   

CREATE TABLE LineItem (
    lineitem_id int not null auto_increment,
    invoice_id int not null default '0',
    book_id int not null default '0',
    quantity int not null default '0',
    
    PRIMARY KEY (lineitem_id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice (invoice_id)
);

DROP DATABASE IF EXISTS lookforbook_users;

CREATE DATABASE lookforbook_users;

USE lookforbook_users;

CREATE TABLE UserPass (
    username varchar(15) not null PRIMARY KEY,
    password varchar(15) not null
);

INSERT INTO UserPass VALUES
    ('jorgenpo', '4s6qymbp');

CREATE TABLE UserRoles (
    username varchar(15) not null,
    rolename varchar(15) not null,

    PRIMARY KEY (username, rolename)
);

INSERT INTO UserRoles VALUES 
    ('jorgenpo', 'administrator');