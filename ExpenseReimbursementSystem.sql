DROP TABLE IF EXISTS EmployeeUsers;
DROP TABLE IF EXISTS ManagerUsers;
DROP TABLE IF EXISTS Reimbursement;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Manager;


CREATE TABLE Employee 
(
	EmployeeID SERIAL PRIMARY KEY,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	EmailAddress varchar(50) NOT NULL,
	Address varchar(50) NOT NULL,
	Status smallint NOT NULL
);

CREATE TABLE Manager
(
	ManagerID SERIAL PRIMARY KEY,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	EmailAddress varchar(50) NOT NULL,
	Address varchar(50) NOT NULL,
	Status smallint NOT NULL
);

CREATE TABLE EmployeeUsers
(
	UserID SERIAL PRIMARY KEY,
	EmployeeID int REFERENCES Employee(EmployeeID),
	Username varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Status smallint NOT NULL
);

CREATE TABLE ManagerUsers
(
	UserID SERIAL PRIMARY KEY,
	ManagerID int REFERENCES Manager(ManagerID),
	Username varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Status smallint NOT NULL
);

CREATE TABLE Reimbursement
(
	ReimbursementID SERIAL PRIMARY KEY,
	EmployeeID int REFERENCES Employee(EmployeeID) NOT NULL,
	ManagerID int REFERENCES Manager(ManagerID),
	Status smallint NOT NULL,
	Title varchar(50) NOT NULL,
	Description varchar(200) NOT NULL,
	Amount numeric(100,2) NOT NULL,
	ReimbursementDate date NOT NULL,
	Receipt bytea NOT NULL
);