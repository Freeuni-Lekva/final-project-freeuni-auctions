DROP DATABASE auctions_db;
CREATE DATABASE IF NOT EXISTS auctions_db;

USE auctions_db;

DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS reviews;

CREATE TABLE users
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password_hash VARCHAR(255),
    user_role VARCHAR(10), /* "admin", "normal", "guest" ? */
    premium TINYINT,
    email VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    balance BIGINT,
    PRIMARY KEY (id),
    suspended TINYINT
    /* premium or not? */
);
ALTER TABLE users CHANGE suspended suspended TINYINT(4) DEFAULT 0 NOT NULL;

CREATE TABLE categories
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE products
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(30),
    user_id BIGINT NOT NULL,
    description VARCHAR(300),
    category_id BIGINT NOT NULL,
    bid_id BIGINT DEFAULT NULL,
    price NUMERIC,
    status VARCHAR(30), /* "available", "sold", "timed_out" */
    image VARCHAR(30),
    date_posted DATE,
    end_date DATE,
    CONSTRAINT userIdFK FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT categoryIdFK FOREIGN KEY (category_id)
        REFERENCES categories(id)
        ON DELETE CASCADE
);

CREATE TABLE bids
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bidder_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
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

    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_id  BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    date TIMESTAMP DEFAULT NOW(),
    price NUMERIC,
    CONSTRAINT saleUserIdFK FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT saleProductIdFK FOREIGN KEY (product_id)
        REFERENCES products(id),
    sale_date DATE
);
CREATE TABLE reports
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    reporter_id BIGINT NOT NULL,
    reported_id BIGINT NOT NULL,
    comment VARCHAR(300),
    resolved TINYINT,
    CONSTRAINT reporterIDFK FOREIGN KEY (reported_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT reportedIDFK FOREIGN KEY (reported_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE reviews
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    product_id BIGINT DEFAULT NULL,
    costumer_id BIGINT NOT NULL,
    reviewText VARCHAR(300),
    CONSTRAINT reviewUserIDFK FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT costIDFK FOREIGN KEY (costumer_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);