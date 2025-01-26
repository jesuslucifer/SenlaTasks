USE hotel_db;

INSERT INTO Clients(fullName, roomNumber, dateCheckIn, dateEvict) VALUES

('Danil Lomaev', 2, '2024-01-24', '2024-01-29'),
('Elena Lomaeva', 2, '2024-01-24', '2024-01-29'),
('Alina Lomaeva', 2, '2024-01-24', '2024-01-29'),
('Dmitriy Lobachev', 9, '2024-01-10', '2024-01-25'),
('Aleksey Lobachev', 9, '2024-01-10', '2024-01-25');

INSERT INTO Rooms(roomNumber, cost, status, capacity, dateCheckIn, dateEvict, countStars)  VALUES
('1', '100', 'FREE', '3', '2020-01-01', '2020-01-01', '3'),
('2', '200', 'BUSY', '3', '2024-01-24', '2024-01-29', '2'),
('3', '150', 'FREE', '3', '2020-01-01', '2020-01-01', '1'),
('4', '400', 'FREE', '3', '2020-01-01', '2020-01-01', '2'),
('5', '300', 'FREE', '3', '2020-01-01', '2020-01-01', '3'),
('6', '200', 'REPAIRED', '3', '2020-01-01', '2020-01-01', '1'),
('7', '100', 'FREE', '3', '2020-01-01', '2020-01-01', '2'),
('8', '100', 'FREE', '3', '2020-01-01', '2020-01-01', '3'),
('9', '170', 'BUSY', '3', '2024-01-10', '2024-01-25', '1'),
('10', '190', 'FREE', '3', '2020-01-01', '2020-01-01', '2');

INSERT INTO Services(serviceName, cost) VALUES
('Massage', '300'),
('Sauna', '200');

INSERT INTO ClientService(clientId, serviceId, serviceDate) VALUES
('1', '1', '2024-01-25'),
('1', '2', '2024-01-26');