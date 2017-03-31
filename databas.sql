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
amountStorage int DEFAULT 500,
deliveryDate date,
deliveryAmount int,
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

/*
  Lägger in kundernas namn och address
*/
INSERT INTO Customers (customerName, address) VALUES('Finkakor AB', 'Helsingborg');
INSERT INTO Customers (customerName, address) VALUES('Småbröd AB', 'Malmö');
INSERT INTO Customers (customerName, address) VALUES('Kaffebröd', 'Landskrona');
INSERT INTO Customers (customerName, address) VALUES('Bjudkakor AB', 'Ystad');
INSERT INTO Customers (customerName, address) VALUES('Kalaskakor AB', 'Trelleborg');
INSERT INTO Customers (customerName, address) VALUES('Partykakor AB', 'Kristianstad');
INSERT INTO Customers (customerName, address) VALUES('Gastkakor', 'Hässleholm');
INSERT INTO Customers (customerName, address) VALUES('Skånekakor', 'Perstorp');

/*
  Lägger in namnen på kakorna
*/

INSERT INTO Products (cookie) VALUES('Nut ring');
INSERT INTO Products (cookie) VALUES('Nut cookie');
INSERT INTO Products (cookie) VALUES('Amneris');
INSERT INTO Products (cookie) VALUES('Tango');
INSERT INTO Products (cookie) VALUES('Almond delight');
INSERT INTO Products (cookie) VALUES('Berliner');

/*
  Lägger in ingredienserna
*/

INSERT INTO Ingredients (ingredient) VALUES('Flour');
INSERT INTO Ingredients (ingredient) VALUES('Butter');
INSERT INTO Ingredients (ingredient) VALUES('Icing sugar');
INSERT INTO Ingredients (ingredient) VALUES('Roasted, chopped nuts');
INSERT INTO Ingredients (ingredient) VALUES('Fine-ground nuts');
INSERT INTO Ingredients (ingredient) VALUES('Ground, roasted nuts');
INSERT INTO Ingredients (ingredient) VALUES('Bread crumbs');
INSERT INTO Ingredients (ingredient) VALUES('Sugar');
INSERT INTO Ingredients (ingredient) VALUES('Egg whites');
INSERT INTO Ingredients (ingredient) VALUES('Chocolate');
INSERT INTO Ingredients (ingredient) VALUES('Marzipan');
INSERT INTO Ingredients (ingredient) VALUES('Eggs');
INSERT INTO Ingredients (ingredient) VALUES('Potato starch');
INSERT INTO Ingredients (ingredient) VALUES('Wheat flour');
INSERT INTO Ingredients (ingredient) VALUES('Sodium bicarbonate');
INSERT INTO Ingredients (ingredient) VALUES('Vanilla');
INSERT INTO Ingredients (ingredient) VALUES('Chopped almonds');
INSERT INTO Ingredients (ingredient) VALUES('Cinnamon');
INSERT INTO Ingredients (ingredient) VALUES('Vanilla sugar');
