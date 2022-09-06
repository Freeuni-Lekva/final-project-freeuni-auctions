CREATE DATABASE IF NOT EXISTS auctions_db;

USE auctions_db;

DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS sales;

CREATE TABLE users (
                       id BINARY(16) PRIMARY KEY NOT NULL,
                       first_name VARCHAR(30),
                       last_name VARCHAR(30),
                       password_hash VARCHAR(255),
                       user_role VARCHAR(6), /* "admin", "normal", "guest" ? */
                       email VARCHAR(255) NOT NULL
    /* premium or not? */
);

CREATE TABLE products (
                          id BINARY(16) PRIMARY KEY NOT NULL,
                          product_name VARCHAR(30),
                          account_id BINARY(16) NOT NULL,
                          description VARCHAR(300),
                          category_id BINARY(16) NOT NULL,
                          bid_id BINARY(16),
                          price NUMERIC,
                          status VARCHAR(30), /* "available", "sold" */
                          date_posted DATE,
                          end_date DATE,
                          CONSTRAINT accountIdFK FOREIGN KEY (account_id)
                              REFERENCES users(id)
                              ON DELETE CASCADE,
                          CONSTRAINT categoryIdFK FOREIGN KEY (category_id)
                              REFERENCES categories(id)
                              ON DELETE CASCADE,
                          CONSTRAINT bidIdFK FOREIGN KEY (bid_id)
                              REFERENCES bids(id)
                              ON DELETE CASCADE
);

CREATE TABLE categories (
                            id BINARY(16) PRIMARY KEY NOT NULL,
                            name VARCHAR(255)
);

CREATE TABLE bids (
                      id BINARY(16) PRIMARY KEY NOT NULL,
                      bidder_id BINARY(16) NOT NULL,
                      product_id BINARY(16) NOT NULL,
                      amount DECIMAL(10, 2),
                      bid_time TIMESTAMP DEFAULT NOW(),
                      CONSTRAINT bidderIdFK FOREIGN KEY (bidder_id)
                          REFERENCES users(id)
                          ON DELETE CASCADE,
                      CONSTRAINT productIdFK FOREIGN KEY (product_id)
                          REFERENCES products(id)
                          ON DELETE CASCADE
);

CREATE TABLE sales
(
    id          BINARY(16) PRIMARY KEY NOT NULL,
    product_id  BINARY(16) NOT NULL,
    user_id     BINARY(16) NOT NULL
);

