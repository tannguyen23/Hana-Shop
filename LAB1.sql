--DROP DATABASE HanaShop
CREATE DATABASE HanaShop
GO
USE HanaShop
GO
CREATE TABLE tblUser (
	UserID			varchar(50)		PRIMARY KEY,
	[Password]		varchar(50)		null,
	[Name]			nvarchar(100)	not null,
	Email			varchar(50)		null,
	RoleID			varchar(50)		not null,
	[Status]		bit				not null
)

CREATE TABLE tblRole(
	RoleID			varchar(50)		PRIMARY KEY,
	[Name]			varchar(50)		not null,
	[Description]	nvarchar(200)null
)
CREATE TABLE tblProduct (
	ProductID		varchar(50)		PRIMARY KEY,
	[Name]			nvarchar(100)	not null,
	CateID			varchar(50)		not null,
	Image_File_Name varchar(30)		not null,
	Quantity		int				not null,
	Price			Decimal			not null,
	TimeOfCreate	Datetime		not null,
	Status			bit				not null
)

CREATE TABLE tblCategory(
	CateID			varchar(50)			PRIMARY KEY,
	[Name]			nvarchar(100)	not null,
	[Description]	nvarchar(200)	null
)

CREATE TABLE tblOrder (
	OrderID			varchar(50)		PRIMARY KEY,
	UserID			varchar(50)		not null,
	Total			Float			not null,
	TimeOfCreate    DateTime		not null
)

CREATE TABLE tblOrderDetail (
	OrderDetailID	varchar(50)		PRIMARY KEY,
	OrderID			varchar(50)		not null,
	ProductID		varchar(50)		not null,
	Quantity		int				not null,
	Price			float			not null
)

CREATE TABLE tblLog(
	LogID			varchar(50)		PRIMARY KEY,
	UserID			varchar(50)		not null,
	ProductID		varchar(50)		not null,
	[Action]		varchar(50)		not null,
	TimeOfCreate	DateTime		not null
)

ALTER TABLE tblOrder
ADD FOREIGN KEY	(UserID)
REFERENCES tblUser(UserID)

ALTER TABLE tblOrderDetail
ADD FOREIGN KEY (OrderID)
REFERENCES tblOrder(OrderID)

ALTER TABLE tblProduct
ADD FOREIGN KEY (CateID)
REFERENCES tblCategory(CateID)

ALTER TABLE tblUser
ADD FOREIGN KEY (RoleID)
REFERENCES	tblRole

ALTER TABLE tblOrderDetail
ADD FOREIGN KEY (ProductID)
REFERENCES tblProduct

ALTER TABLE tblLog
ADD FOREIGN KEY (ProductID)
REFERENCES tblProduct

ALTER TABLE tblLog
ADD FOREIGN KEY	(UserID)
REFERENCES tblUser(UserID)

Insert into tblRole(RoleID,Name) Values
('ad','admin'),
('us','user')

Insert into tblUser(UserID,Password,Name,Email,RoleID,Status) Values
('tannguyen2312','23122000',N'Nguyễn Ngọc Tấn','tanta2359@gmail.com','us',1),
('tienxuan','123456',N'Nguyễn Tiến Xuân','xuan@gmail.com','ad',1),
('lethuan','123456',N'Nguyễn Lê Thuần','thuan@gmail.com','us',1)


Insert into tblCategory(CateID,Name) Values 
(1,'Food'),
(2,'Drink')

Insert into tblProduct(ProductID,Name,CateID,Image_File_Name,Quantity,Price,TimeOfCreate,Status) Values 
('1',N'Bánh mì',1,'pizza.png',50,19500,'20120618 10:34:09 AM',1),
('2',N'Cơm chiên',1,'bapxao.png',50,23000,'20120618 10:34:09 AM',1),
('3',N'Xôi',1,'pizza.png',50,15000,'20120618 10:34:09 AM',1),
('4',N'Bánh canh',1,'bapxao.png',50,25000,'20120618 10:34:09 AM',1),
('5',N'Bánh đa cua',1,'pizza.png',50,21000,'20120618 10:34:09 AM',1),
('6',N'Bún Bò Huế',1,'bapxao.png',50,22000,'20120618 10:34:09 AM',1),
('7',N'Phở',1,'tomchien.png',50,32000,'20120618 10:34:09 AM',1),
('8',N'Cơm cháy',1,'pizza.png',50,15000,'20120618 10:34:09 AM',1),
('9',N'Xôi gà',1,'pizza.png',50,20000,'20120618 10:34:09 AM',1),
('10',N'Coca cola',2,'tomchien.png',50,18000,'20120618 10:34:09 AM',1),
('11',N'Pepsi',2,'bapxao.png',50,18000,'20120618 10:34:09 AM',1),
('12',N'Sinh tố đá bào',2,'pizza.png',50,18000,'20120618 10:34:09 AM',1),
('13',N'Nước mía',2,'bapxao.png',50,18000,'20120618 10:34:09 AM',1),
('14',N'Rau má',2,'pizza.png',50,18000,'20120618 10:34:09 AM',1)