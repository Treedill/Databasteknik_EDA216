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
INSERT INTO Ingredients (ingredient) VALUES('Chopped almonds')
INSERT INTO Ingredients (ingredient) VALUES('Cinnamon');
INSERT INTO Ingredients (ingredient) VALUES('Vanilla sugar');

/*
  Lägger in mängden av alla ingredienser i varje kaka
*/

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(1, 1, 450);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(2, 1, 450);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(3, 1, 190);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(4, 1, 225);

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(5, 2, 750);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(6, 2, 625);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(7, 2, 125);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(8, 2, 375);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(9, 2, 350);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(10, 2, 50);

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(11,3, 750);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(2,3, 250);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(12,3, 250);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(13,3, 25);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(14,3, 25);

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(2,4, 200);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(8,4, 250);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(1,4, 300);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(15,4, 4);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(16,4, 2);

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(2,5, 400);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(8,5, 270);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(17,5, 279);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(1,5, 400);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(18,5, 10);

INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(1,6, 350);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(2,6, 250);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(3,6, 100);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(12,6, 50);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(19,6, 5);
INSERT INTO RecipeItems (ingredientID, cookieID, amount) VALUES(10,6, 50);
