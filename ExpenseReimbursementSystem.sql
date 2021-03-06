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

INSERT INTO Employee (FirstName, LastName, EmailAddress, Address, Status)
VALUES ('Dwight','Schrute','Dwight.Schrute@dundermifflin.com','123 Wallaby Ln',1),
('Jim','Halpert','Jim.Halpert@dundermifflin.com','456 Zoo Ave',1),
('Pam','Beesly','Pam.Beesly@dundermifflin.com','456 Zoo Ave',1),
('Kevin','Malone','Kevin.Malone@dundermifflin.com','333 W 3rd St',1),
('Angela','Martin','Angela.Martin@dundermifflin.com','583 E 5th Dr',1),
('Stanley','Hudson','Stanley.Hudson@dundermifflin.com','443 Scranton Ln',1),
('Meredith','Palmer','Meredith.Palmer@dundermifflin.com','666 Yuri Ct',1),
('Oscar','Martinez','Oscar.Martinez@dundermifflin.com','190 Virtuoso Ln',1),
('Creed','Bratton','Creed.Bratton@dundermifflin.com','The Pit by the Query',1),
('Phyllis','Lapin','Phyllis.Lapin@dundermifflin.com','449 Robert Dr',1),
('Toby','Flenderson','Toby.Flenderson@dundermifflin.com','302 Nowhere Pl',1),
('Darryl','Philblin','Darryl.Philblin@dundermifflin.com','542 Warehouse Zn',1);

CREATE TABLE Manager
(
	ManagerID SERIAL PRIMARY KEY,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	EmailAddress varchar(50) NOT NULL,
	Address varchar(50) NOT NULL,
	Status smallint NOT NULL
);

INSERT INTO Manager (FirstName, LastName, EmailAddress, Address, Status)
VALUES ('Michael','Scott','Michael.Scott@dundermifflin.com','736 Good Pl',1);
INSERT INTO Manager (FirstName, LastName, EmailAddress, Address, Status)
VALUES ('Jan','Levinson','Jan.Levinson@dundermifflin.com','440 Turkey Rd',1);

CREATE TABLE EmployeeUsers
(
	UserID SERIAL PRIMARY KEY,
	EmployeeID int REFERENCES Employee(EmployeeID),
	Username varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Status smallint NOT NULL
);

INSERT INTO EmployeeUsers (EmployeeID, Username, Password, Status)
VALUES (1,'schruted','ilovebeets',1),
(2,'halpertj','bearsbeetsbattlestargalactica',1),
(3,'beeslyp','iloveart',1),
(4,'malonek','snickers',1),
(5,'martina','sprinkles',1),
(6,'palmerm','imnotanalcoholic',1);

CREATE TABLE ManagerUsers
(
	UserID SERIAL PRIMARY KEY,
	ManagerID int REFERENCES Manager(ManagerID),
	Username varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
	Status smallint NOT NULL
);

INSERT INTO ManagerUsers (ManagerID, Username, Password, Status)
VALUES (1,'scottm','thatswhatshesaid',1);
INSERT INTO ManagerUsers (ManagerID, Username, Password, Status)
VALUES (2,'levinsonj','astrid',1);

CREATE TABLE Reimbursement
(
	ReimbursementID SERIAL PRIMARY KEY,
	EmployeeID int REFERENCES Employee(EmployeeID) NOT NULL,
	ManagerID int REFERENCES Manager(ManagerID),
	Status smallint NOT NULL,
	Title varchar(50) NOT NULL,
	Description varchar(500) NOT NULL,
	Amount numeric(100,2) NOT NULL,
	ReimbursementDate date NOT NULL,
	ReceiptLocation varchar(200) NOT NULL
);
