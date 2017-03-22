PRAGMA foreign_keys=OFF;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Pallets;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS RecipeItems;
PRAGMA foreign_keys=ON;

CREATE TABLE Customers(
ssn CHAR(12),
customerName VARCHAR(30),
address VARCHAR(40),
PRIMARY KEY (ssn)
);

CREATE TABLE Orders(
orderNbr int,
placedDate date NOT NULL,
deliverDate date NOT NULL,
ssn char(12),
PRIMARY KEY (orderNbr),
FOREIGN KEY (ssn) references Customers(ssn)
);

CREATE TABLE Pallets(
palletID int,
dateProduced date NOT NULL,
isBlocked int,
dateDelivered date NOT NULL,
cookie VARCHAR(30),
orderNbr int,
PRIMARY KEY (palletID),
FOREIGN KEY (cookie) references Products(cookie),
FOREIGN KEY (orderNbr) references Orders(orderNbr)
);

CREATE TABLE Products(
cookieID int auto_increment,
cookie VARCHAR(30),
PRIMARY KEY (cookieID)
);

CREATE TABLE Ingredients(
ingredientID int auto_increment,
ingredient VARCHAR(30),
amountStorage int,
deliveryDate date NOT NULL,
deliveryAmount int,
PRIMARY KEY (ingridientID)
);

CREATE TABLE OrderItems(
nbrPallets int,
cookie VARCHAR(30),
orderNbr int,
PRIMARY KEY (nbrPallets, cookie, orderNbr),
FOREIGN KEY (cookie) references Products(cookie),
FOREIGN KEY (orderNbr) references Orders(orderNbr)
);

CREATE TABLE RecipeItems(
amount int,
ingredient VARCHAR(30),
cookie VARCHAR(30),
PRIMARY KEY (amount, ingredient, cookie),
FOREIGN KEY (ingredient) references Ingredients(ingredient),
FOREIGN KEY (cookie) references Products(cookie)
);

