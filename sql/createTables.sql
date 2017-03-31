/* 
  Skapar alla tabeller
*/

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
deliveryDate date NOT NULL,
ssn char(12),
PRIMARY KEY (orderNbr),
FOREIGN KEY (ssn) references Customers(ssn)
);

CREATE TABLE Pallets(
palletID int,
dateProduced datetime NOT NULL,
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
deliveryAmount int DEFAULT 500,
PRIMARY KEY (ingredientID)
);

CREATE TABLE OrderItems(
nbrPallets int,
cookie VARCHAR(30),
orderNbr int,
PRIMARY KEY (cookie, orderNbr),
FOREIGN KEY (cookie) references Products(cookie),
FOREIGN KEY (orderNbr) references Orders(orderNbr)
);

CREATE TABLE RecipeItems(
amount int,
ingredientID int,
cookieID int,
PRIMARY KEY (ingredientID, cookieID),
FOREIGN KEY (ingredientID) references Ingredients(ingredientID),
FOREIGN KEY (cookieID) references Products(cookieID)
);

