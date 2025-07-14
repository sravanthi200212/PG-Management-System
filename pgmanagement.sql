CREATE DATABASE pg_management;
USE pg_management;

CREATE TABLE tenants (
    tenant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    gender VARCHAR(10),
    room_no INT,
    rent DOUBLE,
    joining_date DATE,
    leaving_date DATE,
    aadhaar_number VARCHAR(20) UNIQUE,
    occupation VARCHAR(50),
    payment_status VARCHAR(20) DEFAULT 'pending',
    status VARCHAR(10) DEFAULT 'active'
);
SELECT name, phone, status FROM tenants WHERE phone = '7816064876';

SELECT*FROM tenants;

CREATE TABLE rooms (
    room_no INT PRIMARY KEY,
    floor INT,
    capacity INT DEFAULT 1,
    occupied BOOLEAN DEFAULT FALSE
);
select*from rooms;

INSERT INTO rooms (room_no, floor, capacity, occupied) VALUES
(101, 1, 1, TRUE),
(102, 1, 1, TRUE),
(103, 1, 1, TRUE),
(104, 1, 1, FALSE),
(105, 1, 1, TRUE),
(106, 1, 1, FALSE),
(201, 2, 1, TRUE),
(202, 2, 1, TRUE),
(203, 2, 1, FALSE),
(204, 2, 1, FALSE),
(205, 2, 1, TRUE),
(206, 2, 1, FALSE);
SELECT * FROM rooms;
SELECT * FROM tenants;
SELECT * FROM rooms WHERE occupied = FALSE;

