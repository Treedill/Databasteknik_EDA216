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
customerName VARCHAR(30),
address VARCHAR(40),
PRIMARY KEY (customerName)
);

CREATE TABLE Orders(
orderNbr int,
placedDate date NOT NULL,
deliveryDate date NOT NULL,
customerName VARCHAR(30),
PRIMARY KEY (orderNbr),
FOREIGN KEY (customerName) references Customers(customerName)
);

CREATE TABLE Pallets(
palletID integer,
dateProduced datetime NOT NULL,
isBlocked int,
dateDelivered date,
cookie VARCHAR(30),
orderNbr int,
PRIMARY KEY (palletID),
FOREIGN KEY (cookie) references Products(cookie),
FOREIGN KEY (orderNbr) references Orders(orderNbr)
);

CREATE TABLE Products(
cookie VARCHAR(30),
PRIMARY KEY (cookie)
);

CREATE TABLE Ingredients(
ingredient VARCHAR(30),
amountStorage int DEFAULT 5000,
deliveryDate date,
deliveryAmount int,
PRIMARY KEY (ingredient)
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
ingredient VARCHAR(30),
cookie VARCHAR(30),
PRIMARY KEY (ingredient, cookie),
FOREIGN KEY (ingredient) references Ingredients(ingredient),
FOREIGN KEY (cookie) references Products(cookie)
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

/*
Lägger in orders
*/
INSERT INTO Orders (orderNbr, customerName, placedDate, deliveryDate)
VALUES (1, 'Finkakor AB', 2016-03-10, 2016-05-10);
INSERT INTO Orders (orderNbr, customerName, placedDate, deliveryDate)
VALUES (2, 'Finkakor AB', 2016-03-10, 2016-05-10);
INSERT INTO Orders (orderNbr, customerName, placedDate, deliveryDate)
VALUES (3, 'Småbröd AB', 2016-03-19, 2016-05-10);

INSERT INTO OrderItems (cookie, nbrPallets, orderNbr)
VALUES ('Nut ring', 1, 1);
INSERT INTO OrderItems (cookie, nbrPallets, orderNbr)
VALUES ('Nut cookie', 1, 2);
INSERT INTO OrderItems (cookie, nbrPallets, orderNbr)
VALUES ('Tango', 1, 3);

INSERT INTO Pallets (palletID, dateProduced, isBLocked, cookie, orderNbr)
VALUES (1, datetime('now'), 0,  'Nut ring', 1);
INSERT INTO Pallets (palletID, dateProduced, isBLocked, cookie, orderNbr)
VALUES (2, datetime('now'), 0,  'Tango', 3);

/*
Lägger in recepten
*/

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Flour','Nut ring' , 450);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Butter', 'Nut ring', 450);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Icing sugar', 'Nut ring', 190);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Roasted, chopped nuts', 'Nut ring', 225);

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Fine-ground nuts', 'Nut cookie', 750);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Ground, roasted nuts', 'Nut cookie', 625);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Bread crumbs', 'Nut cookie', 125);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Sugar', 'Nut cookie', 375);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Egg whites', 'Nut cookie', 350);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Chocolate', 'Nut cookie', 50);

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Marzipan','Amneris', 750);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Butter','Amneris', 250);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Eggs','Amneris', 250);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Potato starch','Amneris', 25);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Wheat flour','Amneris', 25);

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Butter','Tango', 200);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Sugar','Tango', 250);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Flour','Tango', 300);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Sodium bicarbonate','Tango', 4);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Vanilla','Tango', 2);

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Butter','Almond delight', 400);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Sugar','Almond delight', 270);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Chopped almonds','Almond delight', 270);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Flour','Almond delight', 400);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Cinnamon','Almond delight', 10);

INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Flour','Berliner', 350);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Butter','Berliner', 250);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Icing sugar','Berliner', 100);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Eggs','Berliner', 50);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Vanilla sugar','Berliner', 5);
INSERT INTO RecipeItems (ingredient, cookie, amount) VALUES('Chocolate','Berliner', 50);
