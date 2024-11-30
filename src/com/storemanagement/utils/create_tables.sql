USE StoreManagementSystem_statement;

-- Create Branches Table
CREATE TABLE IF NOT EXISTS Branches (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
                                        address TEXT NOT NULL,
                                        phone_number VARCHAR(15),
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        UNIQUE(name)
);

-- Create Users Table
CREATE TABLE IF NOT EXISTS Users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) UNIQUE NOT NULL,
                                     password_hash VARCHAR(255) NOT NULL,
                                     role ENUM('ADMIN', 'MANAGER', 'EMPLOYEE') NOT NULL,
                                     branch_id INT,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (branch_id) REFERENCES Branches(id)
);

-- Create Employees Table
CREATE TABLE IF NOT EXISTS Employees (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         full_name VARCHAR(100) NOT NULL,
                                         phone_number VARCHAR(15),
                                         role ENUM('ADMIN', 'MANAGER', 'EMPLOYEE') NOT NULL,
                                         branch_id INT,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         FOREIGN KEY (branch_id) REFERENCES Branches(id)
);

-- Create Products Table
CREATE TABLE IF NOT EXISTS Products (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
                                        category VARCHAR(50) NOT NULL,
                                        price DECIMAL(10, 2) NOT NULL,
                                        stock_quantity INT NOT NULL,
                                        branch_id INT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (branch_id) REFERENCES Branches(id)
);

-- Create Customers Table
CREATE TABLE IF NOT EXISTS Customers (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         full_name VARCHAR(100) NOT NULL,
                                         phone_number VARCHAR(15),
                                         customer_type ENUM('NEW', 'RETURNING', 'VIP') NOT NULL,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Sales Table
CREATE TABLE IF NOT EXISTS Sales (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     customer_id INT,
                                     product_id INT,
                                     quantity INT NOT NULL,
                                     branch_id INT,
                                     sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (customer_id) REFERENCES Customers(id),
                                     FOREIGN KEY (product_id) REFERENCES Products(id),
                                     FOREIGN KEY (branch_id) REFERENCES Branches(id)
);

-- Create ChatSessions Table
CREATE TABLE IF NOT EXISTS ChatSessions (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            from_user_id INT,
                                            to_user_id INT,
                                            start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            end_time TIMESTAMP NULL,
                                            FOREIGN KEY (from_user_id) REFERENCES Users(id),
                                            FOREIGN KEY (to_user_id) REFERENCES Users(id)
);

-- Create Logs Table
CREATE TABLE IF NOT EXISTS Logs (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    log_type ENUM('EMPLOYEE', 'CUSTOMER', 'SALE', 'CHAT') NOT NULL,
                                    details TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add Indexes for Performance Optimization
-- Check for existing indexes before adding them
DELIMITER //

CREATE PROCEDURE AddIndexesIfNotExists()
BEGIN
    -- Users Table Indexes
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE() AND table_name = 'Users' AND index_name = 'idx_users_username'
    ) THEN
        CREATE INDEX idx_users_username ON Users (username);
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE() AND table_name = 'Users' AND index_name = 'idx_users_branch_role'
    ) THEN
        CREATE INDEX idx_users_branch_role ON Users (branch_id, role);
    END IF;

    -- Branches Table Index
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE() AND table_name = 'Branches' AND index_name = 'idx_branches_name'
    ) THEN
        CREATE INDEX idx_branches_name ON Branches (name);
    END IF;

    -- Employees Table Indexes
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE() AND table_name = 'Employees' AND index_name = 'idx_employees_branch_role'
    ) THEN
        CREATE INDEX idx_employees_branch_role ON Employees (branch_id, role);
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE() AND table_name = 'Employees' AND index_name = 'idx_employees_full_name'
    ) THEN
        CREATE INDEX idx_employees_full_name ON Employees (full_name);
    END IF;

    -- Repeat for other indexes as needed...
END //

DELIMITER ;

-- Call the procedure to add indexes
CALL AddIndexesIfNotExists();
