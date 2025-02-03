CREATE DATABASE IF NOT EXISTS hotel_db;

USE hotel_db;

CREATE TABLE Clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(25),
    roomNumber INT,
    dateCheckIn DATE,
    dateEvict DATE,
    occupied BOOLEAN DEFAULT TRUE
);

CREATE TABLE Rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roomNumber INT UNIQUE,
    cost INT,
    status ENUM('BUSY', 'FREE', 'REPAIRED'),
    capacity INT,
    countStars INT,
    dateCheckIn DATE NULL,
    dateEvict DATE,
    lockedChangeStatus BOOLEAN NOT NULL DEFAULT TRUE,
    countRecordsHistory INT DEFAULT 3
);

CREATE TABLE Services (
    id INT PRIMARY KEY AUTO_INCREMENT,
    serviceName VARCHAR(25) UNIQUE,
    cost INT
);

CREATE TABLE ClientService (
    clientId INT,
    serviceId INT,
    serviceDate DATE,
    PRIMARY KEY (clientId, serviceId),
    FOREIGN KEY (clientId) REFERENCES Clients(id),
    FOREIGN KEY (serviceId) REFERENCES Services(id)
);