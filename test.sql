DROP DATABASE test_db;
CREATE DATABASE test_db;

USE test_db;

DROP TABLE IF EXISTS bids;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS reviews;

CREATE TABLE test_db.users
like auctions_db.users;

CREATE TABLE test_db.categories
like auctions_db.categories;

CREATE TABLE test_db.products
like auctions_db.products;

CREATE TABLE test_db.bids
like auctions_db.bids;

CREATE TABLE test_db.sales
like auctions_db.sales;

CREATE TABLE test_db.reports
like auctions_db.reports;

CREATE TABLE test_db.reviews
like auctions_db.reviews;