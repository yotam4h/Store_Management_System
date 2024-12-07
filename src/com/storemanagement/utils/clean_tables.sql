-- Clean all tables (disable and enable foreign key checks to prevent constraint errors)
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE Sales;
TRUNCATE TABLE ChatSessions;
TRUNCATE TABLE Logs;
TRUNCATE TABLE Customers;
TRUNCATE TABLE Products;
TRUNCATE TABLE Employees;
TRUNCATE TABLE Users;
TRUNCATE TABLE Branches;

SET FOREIGN_KEY_CHECKS = 1;
