CREATE DATABASE J6Security
GO

USE J6Security
GO

CREATE TABLE roles(
	id VARCHAR(10) NOT NULL,
	[name] NVARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
)
GO

CREATE TABLE accounts(
	username VARCHAR(20) NOT NULL,
	[password] NVARCHAR(100) NOT NULL,
	fullname NVARCHAR(50) NOT NULL,
	email NVARCHAR(50) NOT NULL,
	photo NVARCHAR(50) NOT NULL,
	PRIMARY KEY (username)
)
GO

CREATE TABLE authorities(
	id INT IDENTITY(1,1) NOT NULL,
	username VARCHAR(20) NOT NULL,
	role_id  VARCHAR(10) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (username) REFERENCES accounts(username),
	FOREIGN KEY (role_id) REFERENCES roles(id)
)
GO

INSERT INTO accounts(username, [password], fullname, email, photo)
VALUES ('user1', '123', N'Nguyễn Văn A', 'nguyenvana@gmail.com', 'anh1.jpg'),
		('user2', '123', N'Nguyễn Văn B', 'nguyenvanb@gmail.com', 'anh2.jpg'),
		('user3', '123', N'Nguyễn Văn C', 'nguyenvanc@gmail.com', 'anh3.jpg'),
		('user4', '123', N'Nguyễn Văn D', 'nguyenvand@gmail.com', 'anh4.jpg'),
		('user5', '123', N'Nguyễn Văn E', 'nguyenvane@gmail.com', 'anh5.jpg');

INSERT INTO roles(id, name)
VALUES ('ADMIN', 'Administrators'),
		('USER', 'Users'),
		('GUEST', 'Guests');

INSERT INTO authorities(username, role_id)
VALUES ('user1', 'ADMIN'),
		('user2', 'USER'),
		('user3', 'GUEST'),
		('user3', 'USER');


SELECT * FROM accounts
SELECT * FROM roles
SELECT * FROM authorities