CREATE DATABASE I6Store
GO

USE I6Store
GO

CREATE TABLE account(
	username VARCHAR(50) NOT NULL,
	[password] VARCHAR(50) NOT NULL,
	fullname NVARCHAR(50) NOT NULL,
	email VARCHAR(150) NOT NULL,
	photo VARCHAR(250) NOT NULL,
	PRIMARY KEY(username)
)
GO


CREATE TABLE category(
	id NVARCHAR(50) NOT NULL,
	[name] NVARCHAR(50) NOT NULL,
	PRIMARY KEY(id)
)
GO

CREATE TABLE product(
	id INT IDENTITY(1,1) NOT NULL,
	[name] NVARCHAR(50) NOT NULL,
	[image] VARCHAR(250) NOT NULL,
	price FLOAT NOT NULL,
	create_date DATE NOT NULL,
	available BIT NOT NULL,
	category_id NVARCHAR(50) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(category_id) REFERENCES category(id)
)
GO

CREATE TABLE [order](
	id INT IDENTITY(1,1) NOT NULL,
	username VARCHAR(50) NOT NULL,
	create_date DATE NOT NULL,
	[address] NVARCHAR(250) NOT NULL
	PRIMARY KEY(id),
	FOREIGN KEY(username) REFERENCES account(username)
)
GO

CREATE TABLE order_detail(
	id INT IDENTITY(1,1) NOT NULL,
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	price FLOAT NOT NULL,
	quantity INT NOT NULL, 
	PRIMARY KEY(id),
	FOREIGN KEY(order_id) REFERENCES [order](id),
	FOREIGN KEY(product_id) REFERENCES product(id),
)

INSERT INTO account (username, [password], fullname, email, photo) VALUES
('user1', 'pass1', N'John Doe', 'john1@example.com', 'john1.jpg'),
('user2', 'pass2', N'Jane Smith', 'jane2@example.com', 'jane2.jpg'),
('user3', 'pass3', N'Michael Brown', 'michael3@example.com', 'michael3.jpg'),
('user4', 'pass4', N'Sarah Lee', 'sarah4@example.com', 'sarah4.jpg'),
('user5', 'pass5', N'Chris White', 'chris5@example.com', 'chris5.jpg'),
('user6', 'pass6', N'Emily Clark', 'emily6@example.com', 'emily6.jpg'),
('user7', 'pass7', N'David Green', 'david7@example.com', 'david7.jpg'),
('user8', 'pass8', N'Emma Johnson', 'emma8@example.com', 'emma8.jpg'),
('user9', 'pass9', N'James Carter', 'james9@example.com', 'james9.jpg'),
('user10', 'pass10', N'Sophia Moore', 'sophia10@example.com', 'sophia10.jpg');


INSERT INTO category (id, [name]) VALUES
('cat1', N'Electronics'),
('cat2', N'Books'),
('cat3', N'Clothing'),
('cat4', N'Furniture'),
('cat5', N'Toys'),
('cat6', N'Sports'),
('cat7', N'Health'),
('cat8', N'Automotive'),
('cat9', N'Groceries'),
('cat10', N'Accessories');


INSERT INTO product ([name], [image], price, create_date, available, category_id) VALUES
(N'Smartphone', 'smartphone.jpg', 699.99, '2024-01-10', 1, 'cat1'),
(N'Laptop', 'laptop.jpg', 999.99, '2024-01-15', 1, 'cat1'),
(N'Novel', 'novel.jpg', 15.99, '2024-02-01', 1, 'cat2'),
(N'Cookbook', 'cookbook.jpg', 25.99, '2024-02-05', 1, 'cat2'),
(N'Jeans', 'jeans.jpg', 49.99, '2024-03-01', 1, 'cat3'),
(N'T-shirt', 'tshirt.jpg', 19.99, '2024-03-10', 1, 'cat3'),
(N'Sofa', 'sofa.jpg', 499.99, '2024-04-01', 1, 'cat4'),
(N'Dining Table', 'diningtable.jpg', 299.99, '2024-04-10', 1, 'cat4'),
(N'Action Figure', 'actionfigure.jpg', 29.99, '2024-05-01', 1, 'cat5'),
(N'Board Game', 'boardgame.jpg', 39.99, '2024-05-10', 1, 'cat5');


INSERT INTO [order] (username, create_date, [address]) VALUES
('user1', '2024-06-01', N'123 Elm Street'),
('user2', '2024-06-02', N'456 Oak Street'),
('user3', '2024-06-03', N'789 Pine Avenue'),
('user4', '2024-06-04', N'321 Maple Drive'),
('user5', '2024-06-05', N'654 Cedar Lane'),
('user6', '2024-06-06', N'987 Birch Road'),
('user7', '2024-06-07', N'159 Walnut Court'),
('user8', '2024-06-08', N'753 Cherry Blvd'),
('user9', '2024-06-09', N'951 Spruce Terrace'),
('user10', '2024-06-10', N'357 Aspen Circle');


INSERT INTO order_detail (order_id, product_id, price, quantity) VALUES
(1, 1, 699.99, 1),
(1, 2, 999.99, 1),
(2, 3, 15.99, 2),
(2, 4, 25.99, 1),
(3, 5, 49.99, 3),
(3, 6, 19.99, 2),
(4, 7, 499.99, 1),
(4, 8, 299.99, 1),
(5, 9, 29.99, 4),
(5, 10, 39.99, 1);

SELECT * FROM account
SELECT * FROM category
SELECT * FROM product
SELECT * FROM [order]
SELECT * FROM order_detail