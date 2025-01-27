CREATE TABLE Employees (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE Departments (
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(255) UNIQUE NOT NULL,
     is_read_only BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Employee_Departments (
    employee_id INT NOT NULL,
    department_id INT NOT NULL
);
