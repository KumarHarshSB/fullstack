CREATE TABLE Employees (
   id INT PRIMARY KEY,
   name VARCHAR(255),
   email VARCHAR(255)
);

CREATE TABLE Departments (
     id INT PRIMARY KEY,
     name VARCHAR(255),
     read_only BOOLEAN NOT NULL,
     mandatory BOOLEAN NOT NULL
);

CREATE TABLE Map_Employees_Departments (
    employee_id INT,
    department_id INT,
    PRIMARY KEY (employee_id, department_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES Departments(id) ON DELETE CASCADE
);
