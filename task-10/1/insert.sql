USE `10task1`;

INSERT INTO product (maker, model, type) VALUES

('Lenovo', 'IdeaPad 3', 'Laptop'),
('Lenovo', 'IdeaPad 1', 'Laptop'),
('Lenovo', 'IdeaPad 2', 'Laptop'),
('Acer', 'Nitro 17', 'Laptop'),
('Acer', 'Nitro 18', 'Laptop'),
('B', 'Nitro 19', 'Laptop'),
('Apple', 'MackBook Air', 'Laptop'),
('Apple', 'MackBook Pro', 'Laptop'),
('Apple', 'MackBook Pro Retina', 'Laptop'),
('MSI', 'Raider GE85', 'Laptop'),
('MSI', 'Raider GE84', 'Laptop'),
('MSI', 'Raider GE86', 'Laptop'),

('HP', 'Pavilion', 'PC'),
('HP', 'Pavilion Gaming', 'PC'),
('B', 'Pavilion 5', 'PC'),
('A', 'Aspire Gaming', 'PC'),
('A', 'Aspire TC', 'PC'),
('A', 'Aspire', 'PC'),
('Dell', 'XPS 13', 'PC'),
('Dell', 'X 13', 'PC'),
('B', 'XPS 11', 'PC'),
('Razer', 'Razer Blade', 'PC'),
('Razer', 'Razer Blade X', 'PC'),
('Samsung', 'Razer Blade XR', 'PC'),

('Canon', 'Pixma G540', 'Printer'),
('Canon', 'Pixma R240', 'Printer'),
('Canon', 'Pixma G600', 'Printer'),
('B', 'L1250', 'Printer'),
('B', 'L1254', 'Printer'),
('B', 'L1261', 'Printer'),
('Pantum', 'P2207', 'Printer'),
('Pantum', 'P2211', 'Printer'),
('Pantum', 'P2103', 'Printer'),
('Samsung', 'SL-M2020', 'Printer'),
('Samsung', 'SM-M2022', 'Printer'),
('Samsung', 'SL-M2023', 'Printer');

INSERT INTO laptop (model, speed, ram, hd, price, screen) VALUES

('IdeaPad 3', '300', '100000', '100', '511.99', '30'),
('IdeaPad 1', '340', '8000', '120', '4630.99', '25'),
('IdeaPad 2', '280', '8000', '160', '200.99', '22'),
('Nitro 17', '600', '10000', '80', '3800.33', '31'),
('Nitro 18', '700', '100000', '40', '350.15', '22'),
('Nitro 19', '900', '100000', '320', '499.99', '30'),
('MackBook Air', '320', '8000', '40', '1550.01', '28'),
('MackBook Pro', '340', '10000', '160', '520.28', '31'),
('MackBook Pro Retina', '300', '10000', '80', '280.31', '17'),
('Raider GE85', '200', '7000', '20', '120.11', '30'),
('Raider GE84', '600', '6000', '40', '233.33', '27'),
('Raider GE86', '700', '9000', '80', '189.15', '28');

INSERT INTO pc (model, speed, ram, hd, cd, price) VALUES
('Pavilion', '450', '10000', '8', '8x', '800.33'),
('Pavilion Gaming', '600', '10000', '16', '24x', '356.33'),
('Pavilion 5', '800', '8000', '5', '5x', '255.55'),
('Aspire Gaming', '700', '10000', '16', '16x', '900.00'),
('Aspire', '300', '8000', '8', '4x', '120.31'),
('Aspire TC', '400', '8000', '8', '4x', '130.40'),
('XPS 13', '450', '8000', '8', '8x', '102.31'),
('X 13', '400', '8000', '12', '12x', '110.31'),
('XPS 11', '1200', '4000', '8', '8x', '70.21'),
('Razer Blade', '1000', '8000', '16', '16x', '533.33'),
('Razer Blade X', '1000', '12000', '16', '24x', '455.55'),
('Razer Blade XR', '1200', '4000', '16', '12x', '666.66');

INSERT INTO printer(model, color, type, price) VALUES
('Pixma G540', 'y', 'Laser', '29.30'),
('Pixma R240', 'n', 'Jet', '40.11'),
('Pixma G600', 'y', 'Laser', '10.31'),
('L1250', 'y', 'Laser', '32.12'),
('L1254', 'y', 'Jet', '90.99'),
('L1261', 'n', 'Matrix', '20.31'),
('P2207', 'y', 'Matrix', '10.31'),
('P2211', 'n', 'Matrix', '15.22'),
('P2103', 'n', 'Matrix', '10.31'),
('SL-M2020', 'y', 'Laser', '50.35'),
('SM-M2022', 'y', 'Laser', '60.78'),
('SL-M2023', 'y', 'Laser', '90.99');






