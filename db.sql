-- Create the database
CREATE DATABASE IF NOT EXISTS Vroom;
USE Vroom;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS userDetails;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS insurance;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS rental;
DROP TABLE IF EXISTS booking;
SET FOREIGN_KEY_CHECKS = 1;

-- DDLs
-- ===============================================================
CREATE TABLE users (
    UID INT PRIMARY KEY AUTO_INCREMENT,
    userType INT NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE userDetails (
    UID INT PRIMARY KEY,
    fullName VARCHAR(100),
    address TEXT,
    contact VARCHAR(15),
    driver_license_number VARCHAR(20) UNIQUE NOT NULL,
    FOREIGN KEY (UID) REFERENCES users(UID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS cars (
    regNo VARCHAR(20) NOT NULL,
    owner_id INT NOT NULL,
    model VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    rate DECIMAL(10, 2) NOT NULL,
    status ENUM('Available', 'Maintenance', 'Booked') DEFAULT 'Maintenance',
    fuelType ENUM ('Petrol', 'Diesel', 'Electric') NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users(UID) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (regNo)
);

CREATE TABLE IF NOT EXISTS booking (
    BID INT AUTO_INCREMENT PRIMARY KEY,
    regNo VARCHAR(20) NOT NULL,
    fromDate DATE NOT NULL,
    tillDate DATE NOT NULL,
    FOREIGN KEY (regNo) REFERENCES cars(regNo) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE IF NOT EXISTS insurance (
    IID INT AUTO_INCREMENT PRIMARY KEY,
    regNo VARCHAR(20) NOT NULL,
    provider_name VARCHAR(255) NOT NULL,
    policy_number VARCHAR(255) UNIQUE NOT NULL,
    coverage_amount DECIMAL(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('Valid', 'Invalid') DEFAULT 'Invalid',
    FOREIGN KEY (regNo) REFERENCES cars(regNo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS rental (
    rentID INT AUTO_INCREMENT PRIMARY KEY,
    BID INT DEFAULT NULL,
    customer_id INT DEFAULT NULL,
    totalAmount DECIMAL(10, 2) NOT NULL,
    status ENUM('Active', 'Completed', 'Canceled') DEFAULT 'Active',
    FOREIGN KEY (BID) REFERENCES booking(BID) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES users(UID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS payment (
    PID INT AUTO_INCREMENT PRIMARY KEY,
    rentID INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_method ENUM('Credit Card', 'Cash', 'Bank Transfer') ,
    status ENUM('Paid', 'Pending', 'Failed') DEFAULT 'Pending',
    transactionID VARCHAR(50) NOT NULL,
    FOREIGN KEY (rentID) REFERENCES rental(rentID)
);

-- =====================================================================================

-- Insert data into users table
INSERT INTO users (userType, username, password) VALUES
(1, 'john_doe', 'password123'),
(2, 'jane_smith', 'password456'),
(1, 'alex_williams', 'password789'),
(2, 'mary_jones', 'password321'),
(1, 'michael_brown', 'password654');


-- Insert data into userDetails table
INSERT INTO userDetails (UID, fullName, address, contact, driver_license_number) VALUES
(1, 'John Doe', '123 Elm Street, Springfield, IL', '555-1234', 'D123456789'),
(2, 'Jane Smith', '456 Oak Avenue, Lincoln, NE', '555-5678', 'D987654321'),
(3, 'Alex Williams', '789 Pine Road, Boston, MA', '555-1122', 'D123987654'),
(4, 'Mary Jones', '321 Maple Boulevard, Austin, TX', '555-3344', 'D321456987'),
(5, 'Michael Brown', '654 Cedar Lane, Denver, CO', '555-5566', 'D654321123');


-- Insert data into cars table
INSERT INTO cars (regNo, owner_id, model, capacity, rate, status, fuelType) VALUES
('ABC123', 1, 'Toyota Camry', 5, 50.00, 'Available', 'Petrol'),
('DEF456', 2, 'Honda Accord', 5, 60.00, 'Booked', 'Diesel'),
('GHI789', 3, 'BMW 320i', 5, 120.00, 'Maintenance', 'Electric'),
('JKL012', 4, 'Mercedes-Benz A-Class', 4, 100.00, 'Available', 'Petrol'),
('MNO345', 5, 'Audi A4', 5, 80.00, 'Available', 'Diesel'),
('PQR678', 1, 'Nissan Altima', 5, 55.00, 'Booked', 'Electric'),
('STU901', 2, 'Ford Focus', 5, 45.00, 'Available', 'Petrol'),
('VWX234', 3, 'Chevrolet Malibu', 5, 70.00, 'Available', 'Electric'),
('YZA567', 4, 'Tesla Model 3', 5, 150.00, 'Booked', 'Diesel'),
('BCD890', 5, 'Ford Mustang', 2, 95.00, 'Available', 'Petrol'),
('EFG123', 1, 'Kia Optima', 5, 60.00, 'Maintenance', 'Diesel'),
('HIJ456', 2, 'Hyundai Sonata', 5, 65.00, 'Booked', 'Electric'),
('KLM789', 3, 'Subaru Impreza', 5, 55.00, 'Available', 'Petrol'),
('NOP012', 4, 'Chevrolet Camaro', 4, 110.00, 'Available', 'Electric'),
('QRS345', 5, 'Mazda 6', 5, 75.00, 'Available', 'Diesel');


-- Insert data into booking table
INSERT INTO booking (regNo, fromDate, tillDate) VALUES
('ABC123', '2025-03-01', '2025-03-05'),
('DEF456', '2025-03-02', '2025-03-06'),
('GHI789', '2025-03-07', '2025-03-10'),
('JKL012', '2025-03-08', '2025-03-12'),
('MNO345', '2025-03-09', '2025-03-15'),
('PQR678', '2025-03-11', '2025-03-14'),
('STU901', '2025-03-13', '2025-03-17'),
('VWX234', '2025-03-15', '2025-03-18'),
('YZA567', '2025-03-16', '2025-03-20'),
('BCD890', '2025-03-17', '2025-03-21'),
('EFG123', '2025-03-18', '2025-03-22'),
('HIJ456', '2025-03-19', '2025-03-23'),
('KLM789', '2025-03-20', '2025-03-24'),
('NOP012', '2025-03-21', '2025-03-25'),
('QRS345', '2025-03-22', '2025-03-26');


-- Insert data into insurance table
INSERT INTO insurance (regNo, provider_name, policy_number, coverage_amount, start_date, end_date) VALUES
('ABC123', 'StateFarm', 'SF12345', 50000.00, '2025-03-01', '2026-03-01'),
('DEF456', 'AllState', 'AS67890', 60000.00, '2025-03-02', '2026-03-02'),
('GHI789', 'Geico', 'G1234567', 70000.00, '2025-03-03', '2026-03-03'),
('JKL012', 'Progressive', 'PR98765', 80000.00, '2025-03-04', '2026-03-04'),
('MNO345', 'Farmers', 'FM56789', 55000.00, '2025-03-05', '2026-03-05'),
('PQR678', 'StateFarm', 'SF22334', 50000.00, '2025-03-06', '2026-03-06'),
('STU901', 'AllState', 'AS44556', 60000.00, '2025-03-07', '2026-03-07'),
('VWX234', 'Geico', 'G7891234', 70000.00, '2025-03-08', '2026-03-08'),
('YZA567', 'Progressive', 'PR34567', 80000.00, '2025-03-09', '2026-03-09'),
('BCD890', 'Farmers', 'FM89012', 55000.00, '2025-03-10', '2026-03-10'),
('EFG123', 'StateFarm', 'SF33445', 50000.00, '2025-03-11', '2026-03-11'),
('HIJ456', 'AllState', 'AS66778', 60000.00, '2025-03-12', '2026-03-12'),
('KLM789', 'Geico', 'G2345678', 70000.00, '2025-03-13', '2026-03-13'),
('NOP012', 'Progressive', 'PR98723', 80000.00, '2025-03-14', '2026-03-14'),
('QRS345', 'Farmers', 'FM12345', 55000.00, '2025-03-15', '2026-03-15');


-- Insert data into rental table
INSERT INTO rental (BID, customer_id, totalAmount, status) VALUES
(1, 1, 200.00, 'Active'),
(2, 2, 250.00, 'Completed'),
(3, 3, 300.00, 'Canceled'),
(4, 4, 400.00, 'Active'),
(5, 5, 150.00, 'Completed'),
(6, 1, 180.00, 'Active'),
(7, 2, 220.00, 'Active'),
(8, 3, 350.00, 'Canceled'),
(9, 4, 320.00, 'Completed'),
(10, 5, 280.00, 'Active'),
(11, 1, 250.00, 'Completed'),
(12, 2, 200.00, 'Active'),
(13, 3, 280.00, 'Canceled'),
(14, 4, 220.00, 'Completed'),
(15, 5, 210.00, 'Active');


-- Insert data into payment table
INSERT INTO payment (rentID, amount, payment_date, payment_method, status, transactionID) VALUES
(1, 200.00, '2025-03-01', 'Credit Card', 'Paid', 'TXN123'),
(2, 250.00, '2025-03-02', 'Cash', 'Paid', 'TXN124'),
(3, 300.00, '2025-03-03', 'Bank Transfer', 'Failed', 'TXN125'),
(4, 400.00, '2025-03-04', 'Credit Card', 'Paid', 'TXN126'),
(5, 150.00, '2025-03-05', 'Cash', 'Paid', 'TXN127'),
(6, 180.00, '2025-03-06', 'Credit Card', 'Paid', 'TXN128'),
(7, 220.00, '2025-03-07', 'Bank Transfer', 'Pending', 'TXN129'),
(8, 350.00, '2025-03-08', 'Credit Card', 'Paid', 'TXN130'),
(9, 320.00, '2025-03-09', 'Cash', 'Paid', 'TXN131'),
(10, 280.00, '2025-03-10', 'Credit Card', 'Paid', 'TXN132'),
(11, 250.00, '2025-03-11', 'Bank Transfer', 'Pending', 'TXN133'),
(12, 200.00, '2025-03-12', 'Cash', 'Failed', 'TXN134'),
(13, 280.00, '2025-03-13', 'Credit Card', 'Paid', 'TXN135'),
(14, 220.00, '2025-03-14', 'Bank Transfer', 'Paid', 'TXN136'),
(15, 210.00, '2025-03-15', 'Cash', 'Paid', 'TXN137');




