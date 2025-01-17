CREATE DATABASE 10task1;

USE 10task1;

CREATE TABLE product
(
    maker VARCHAR(10),
    model VARCHAR(50) PRIMARY KEY UNIQUE,
    type VARCHAR(50)
);

CREATE TABLE laptop
(
    code INT AUTO_INCREMENT UNIQUE,
    model VARCHAR(50),
    speed SMALLINT,
    ram INT,
    hd REAL,
    price DECIMAL(10,2),
    screen TINYINT,
    FOREIGN KEY (model) REFERENCES product (model)
);

CREATE TABLE pc
(
    code INT AUTO_INCREMENT UNIQUE,
    model VARCHAR(50),
    speed SMALLINT,
    ram INT,
    hd REAL,
    cd VARCHAR(10),
    price DECIMAL(10,2),
    FOREIGN KEY (model) REFERENCES product (model)
);

CREATE TABLE printer
(
    code INT AUTO_INCREMENT UNIQUE,
    model VARCHAR(50),
    color CHAR(1),
    type VARCHAR(10),
    price DECIMAL(10,2),
    FOREIGN KEY (model) REFERENCES product (model)
)