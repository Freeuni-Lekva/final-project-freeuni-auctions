CREATE DATABASE auctions_db;

USE auctions_db;

CREATE TABLE users (
    id BINARY(16) NOT NULL primary key,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    password_hash VARCHAR(255),
    user_role VARCHAR(6), --"admin", "normal", "guest" ?
    email VARCHAR(255) NOT NULL
    -- premium or not?
);

CREATE TABLE categories (
     id BINARY(16) NOT NULL primary key,
     name VARCHAR(255)
);

CREATE TABLE bids (
    id BINARY(16) NOT NULL primary key,
    bidder_id BINARY(16) NOT NULL foreign key,
    product_id BINARY(16) NOT NULL foreign key,
    amount DECIMAL(10, 2),
    bid_time TIMESTAMP
);



