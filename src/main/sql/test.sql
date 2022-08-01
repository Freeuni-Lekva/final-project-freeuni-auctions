CREATE DATABASE IF NOT EXISTS test_db;

USE test_db;

/* after the actual database is ready, can change table creation logic to the following:

   CREATE TABLE test_db.bids
   LIKE auctions_db.bids;

   etc

 */

DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

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
    id BINARY(16) PRIMARY KEY NOT NULL
    /* TODO */
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
