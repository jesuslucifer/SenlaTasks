CREATE DATABASE IF NOT EXISTS hotel_db;

USE hotel_db;

CREATE TABLE Clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(25),
    roomNumber INT,
    dateCheckIn DATE,
    dateEvict DATE
);

CREATE TABLE Rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roomNumber INT,
    cost INT,
    status ENUM('BUSY', 'FREE', 'REPAIRED'),
    capacity INT,
    countStars INT,
    dateCheckIn DATE,
    dateEvict DATE
);

CREATE TABLE Services (
    id INT PRIMARY KEY AUTO_INCREMENT,
    serviceName VARCHAR(25),
    cost INT
);