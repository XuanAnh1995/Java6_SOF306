CREATE DATABASE SOS
GO

USE SOS
GO

CREATE TABLE Categories(
	Id INT IDENTITY(1,1) NOT NULL,
	[Name] NVARCHAR(50) NOT NULL,
	PRIMARY KEY(Id)
)
GO

CREATE TABLE Roles(
	Id VARCHAR(10) NOT NULL,
	[Name] VARCHAR(50) NOT NULL,
	PRIMARY KEY(Id)
)
GO

CREATE TABLE Accounts(
	[Username] VARCHAR(50) NOT NULL,
	[Password] VARCHAR(50) NOT NULL,
	Fullname NVARCHAR(50) NOT NULL,
	Email VARCHAR(50) NOT NULL,
	Photo VARCHAR(50) NOT NULL,
	PRIMARY KEY([Username])
)
GO

CREATE TABLE Authorities(
	Id INT IDENTITY(1,1) NOT NULL,
	[Username] VARCHAR(50) NOT NULL,
	RoleId VARCHAR(10) NOT NULL,
	PRIMARY KEY(Id),
	FOREIGN KEY(RoleId) REFERENCES Roles(ID),
	FOREIGN KEY([Username]) REFERENCES Accounts([Username])
)
GO

CREATE TABLE Orders(
	Id INT IDENTITY(1,1) NOT NULL,
	[Username] VARCHAR(50) NOT NULL,
	CreateDate DATE NOT NULL,
	Address NVARCHAR(250) NOT NULL,
	PRIMARY KEY(Id),
	FOREIGN KEY([Username]) REFERENCES Accounts([Username])
)
GO

CREATE TABLE Products(
	Id INT IDENTITY(1,1) NOT NULL,
	[Name] NVARCHAR(250) NOT NULL,
	[Image] NVARCHAR(250) NOT NULL,
	Price FLOAT NOT NULL,
	CreateDate DATE NOT NULL,
	Available BIT NOT NULL,
	CategoryId INT NOT NULL,
	PRIMARY KEY(Id),
	FOREIGN KEY(CategoryId) REFERENCES Categories(Id)
)
GO

CREATE TABLE OrderDetails(
	Id INT IDENTITY(1,1) NOT NULL,
	OrderId INT NOT NULL,
	ProductId INT NOT NULL,
	Price FLOAT NOT NULL,
	Quantity INT NOT NULL,
	PRIMARY KEY(Id),
	FOREIGN KEY(OrderId) REFERENCES Orders(Id),
	FOREIGN KEY(ProductId) REFERENCES Products(Id)
)
GO

-- Thêm dữ liệu vào bảng Roles (Vai trò)
INSERT INTO Roles (Id, [Name])
VALUES ('ADMIN', 'Administrators'),
		('USER', 'Users'),
		('GUEST', 'Guests');

-- Thêm dữ liệu vào bảng Authorities (Quyền hạn)
INSERT INTO Authorities ([Username], RoleId)
VALUES 
('user1', 'ADMIN'),
('user2', 'USER'),
('user3', 'GUEST'),
('user3', 'USER');

-- Thêm dữ liệu vào bảng Categories (Danh mục sản phẩm)
INSERT INTO Categories ([Name]) 
VALUES 
(N'Áo thun'),
(N'Áo sơ mi'),
(N'Áo khoác'),
(N'Áo len'),
(N'Áo vest'),
(N'Áo dài');

SELECT * FROM Categories

-- Thêm dữ liệu vào bảng Products cho mỗi danh mục
-- Áo thun
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo thun trắng', 'aothun_trang.jpg', 150.00, '2024-01-01', 1, 1),
(N'Áo thun đen', 'aothun_den.jpg', 160.00, '2024-01-02', 1, 1),
(N'Áo thun xanh', 'aothun_xanh.jpg', 170.00, '2024-01-03', 1, 1),
(N'Áo thun đỏ', 'aothun_do.jpg', 180.00, '2024-01-04', 1, 1),
(N'Áo thun xám', 'aothun_xam.jpg', 190.00, '2024-01-05', 1, 1);

-- Áo sơ mi
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo sơ mi trắng', 'somi_trang.jpg', 200.00, '2024-01-05', 1, 2),
(N'Áo sơ mi caro', 'somi_caro.jpg', 210.00, '2024-01-06', 1, 2),
(N'Áo sơ mi kẻ sọc', 'somi_kesoc.jpg', 220.00, '2024-01-07', 1, 2),
(N'Áo sơ mi denim', 'somi_denim.jpg', 230.00, '2024-01-08', 1, 2),
(N'Áo sơ mi đỏ', 'somi_do.jpg', 240.00, '2024-01-09', 1, 2);

-- Áo khoác
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo khoác jean', 'aokhoac_jean.jpg', 350.00, '2024-01-10', 1, 3),
(N'Áo khoác da', 'aokhoac_da.jpg', 400.00, '2024-01-11', 1, 3),
(N'Áo khoác bomber', 'aokhoac_bomber.jpg', 450.00, '2024-01-12', 1, 3),
(N'Áo khoác măng tô', 'aokhoac_mangto.jpg', 500.00, '2024-01-13', 1, 3),
(N'Áo khoác parka', 'aokhoac_parka.jpg', 550.00, '2024-01-14', 1, 3);

-- Áo len
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo len cổ tròn', 'aolen_cotron.jpg', 250.00, '2024-02-01', 1, 4),
(N'Áo len cổ lọ', 'aolen_collor.jpg', 260.00, '2024-02-02', 1, 4),
(N'Áo len dệt kim', 'aolen_detkim.jpg', 270.00, '2024-02-03', 1, 4),
(N'Áo len vặn thừng', 'aolen_vanthung.jpg', 280.00, '2024-02-04', 1, 4),
(N'Áo len tay dài', 'aolen_taydai.jpg', 290.00, '2024-02-05', 1, 4);

-- Áo vest
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo vest nam', 'vest_nam.jpg', 500.00, '2024-02-15', 1, 5),
(N'Áo vest nữ', 'vest_nu.jpg', 550.00, '2024-02-16', 1, 5),
(N'Áo vest công sở', 'vest_congso.jpg', 600.00, '2024-02-17', 1, 5),
(N'Áo vest tối màu', 'vest_toimau.jpg', 650.00, '2024-02-18', 1, 5),
(N'Áo vest sáng màu', 'vest_sangmau.jpg', 700.00, '2024-02-19', 1, 5);

-- Áo dài
INSERT INTO Products ([Name], [Image], Price, CreateDate, Available, CategoryId) 
VALUES 
(N'Áo dài truyền thống', 'aodai_truyenthong.jpg', 700.00, '2024-03-01', 1, 6),
(N'Áo dài cách tân', 'aodai_cachtan.jpg', 750.00, '2024-03-02', 1, 6),
(N'Áo dài màu đỏ', 'aodai_do.jpg', 800.00, '2024-03-03', 1, 6),
(N'Áo dài trắng', 'aodai_trang.jpg', 850.00, '2024-03-04', 1, 6),
(N'Áo dài thêu', 'aodai_theu.jpg', 900.00, '2024-03-05', 1, 6);

-- Thêm dữ liệu vào bảng Accounts (Tài khoản người dùng)
INSERT INTO Accounts ([Username], [Password], Fullname, Email, Photo)
VALUES 
('user1', '123', N'Nguyễn Văn A', 'nguyenvana@gmail.com', 'anh1.jpg'),
('user2', '123', N'Nguyễn Văn B', 'nguyenvanb@gmail.com', 'anh2.jpg'),
('user3', '123', N'Nguyễn Văn C', 'nguyenvanc@gmail.com', 'anh3.jpg'),
('user4', '123', N'Nguyễn Văn D', 'nguyenvand@gmail.com', 'anh4.jpg'),
('user5', '123', N'Nguyễn Văn E', 'nguyenvane@gmail.com', 'anh5.jpg');


SELECT * FROM Products
SELECT * FROM Accounts

-- Thêm dữ liệu vào bảng Orders (Đơn hàng)
INSERT INTO Orders ([Username], CreateDate, Address) 
VALUES 
('user1', '2024-02-01', N'123 Đường ABC'),  -- Đơn hàng 1 của user1
('user2', '2024-02-05', N'789 Đường XYZ'),  -- Đơn hàng 1 của user2
('user3', '2024-02-07', N'654 Đường JKL'),  -- Đơn hàng 1 của user3
('user4', '2024-02-20', N'246 Đường STU'),  -- Đơn hàng 1 của user4
('user5', '2024-02-22', N'258 Đường YZ');  -- Đơn hàng 1 của user5


SELECT * FROM Orders

-- Thêm dữ liệu vào bảng OrderDetails (Chi tiết đơn hàng)
INSERT INTO OrderDetails (OrderId, ProductId, Price, Quantity) 
VALUES 
-- Đơn hàng của user2
(2, 5, 260.00, 3),  -- user2 mua 3 chiếc Áo len cổ lọ

-- Đơn hàng của user3
(3, 6, 270.00, 1),  -- user3 mua 1 chiếc Áo len dệt kim
(3, 7, 500.00, 2),  -- user3 mua 2 chiếc Áo khoác jean

-- Đơn hàng của user4
(4, 8, 500.00, 1),  -- user4 mua 1 chiếc Áo khoác da
(4, 9, 550.00, 1),  -- user4 mua 1 chiếc Áo khoác bomber

-- Đơn hàng của user5
(5, 10, 700.00, 1), -- user5 mua 1 chiếc Áo vest nam
(5, 11, 750.00, 2); -- user5 mua 2 chiếc Áo dài truyền thống



SELECT * FROM Categories;
SELECT * FROM Products;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;
SELECT * FROM Accounts;
SELECT * FROM Roles;
SELECT * FROM Authorities;


---- Bảng con (liên quan đến khóa ngoại)
--DELETE FROM OrderDetails;
--DELETE FROM Orders;
--DELETE FROM Authorities;

---- Bảng độc lập hoặc bảng cha
--DELETE FROM Products;
--DELETE FROM Categories;
--DELETE FROM Accounts;
--DELETE FROM Roles;
