# Databasteknik_EDA216
Databasteknik för D &amp;&amp; C programmet för LTH, våren 2017. Inlämningsuppgift/projekt

Johannes Törnblom, dic15jto@student.lu.se, C15

Jenny Martinsson, dic15jma@student.lu.se, C15

## Introduktion
Projektet handlar om att göra ett databassystem åt Krusty Kookies Sweden AB. Vi ska implementera en databas och ett användargränssnitt som hanterar allting som rör produktion, blockering och sökning av pallar.

## Noteringar om vilka krav vi uppfyller/inte uppfyller
#### Produktion
>A pallet is considered to be produced when the pallet label is read at the entrance to the deep-freeze storage. The pallet number, product name, and date and time of production is registered in the database. The pallet number is unique.

>At any time, we must be able to check how many pallets of a product that have been produced during a specific time.
#### Råmaterial
>When a pallet is produced, the raw materials storage must be updated. We must be able to check the amount in store of each ingredient, and to see when, and how much of, an ingredient was last delivered into storage.

#### Recept
>We need an interface to the collection of recipes (appendix A), where we can study and update recipes. We also need a facility for entering new recipes. We don’t change recipes during production.
#### Producerade pallar
>As we mentioned earlier, pallets in the deep-freeze storage may be blocked. An order to block a pallet will always come before the pallet has been delivered. This is due to the new investments in our laboratory, where the analysis process is completely automated.

>We must be able to trace each pallet. For instance, we need to see all information about a pallet with a given number (the contents of the pallet, the location of the pallet, if the pallet is delivered and in that case to whom, etc.). We must also be able to see which pallets that contain a certain product and which pallets that have been produced during a certain time interval.

>Blocked products are of special interest. We need to find out which products that are blocked, and also which pallets that contain a certain blocked product.

>Finally, we must be able to check which pallets that have been delivered to a given customer, and the date and time of delivery.

#### Beställning och produktplanering
>Orders must be registered in the database. For production planning purposes, we must have a facility to see all orders that are to be delivered during a specific time period.

>The production planning is manual. At the end of each week, production for the following week is planned, using the orders for the following weeks as input. We cannot produce "on demand", since it takes time to set up a production line for a new kind of cookie (mixers have to be cleaned, for example).

#### Leverans
>Before pallets are loaded into the freezer trucks, a loading order is created. The order contains information regarding the customers and the number of pallets to be delivered.

>When pallets are taken out of deep-freeze storage the pallet label is read. When the truck is loaded, the driver receives a loading bill (identical to the loading order, but contains a field where the customer can acknowledge reception of the delivery). The loading bill data need not be saved in the database.

>When the loading bill has been printed, the data regarding delivered pallets must be updated with customer data and date of delivery.
## Outline
Vi har använt oss av SQLite för vår databas, och en javaapplication som kommunicerar med vår databas genom JDBC. 
## ER-diagram

## Relationer

customers(**ssn**, customerName, address)

orders(**orderNbr**, placedDate, deliveryDate, *ssn*)

pallets(**palletID**, dateProduced, isBlocked, dateDelivered, *cookieID*, *orderNbr*)

products(**cookieID**, cookie)

ingredients(**ingredientID**,ingredient, amountStorage, deliveryDate, deliveryAmount)

recipeItems(amount, **_ingredientID_, _cookieID_**)

orderItems(nbrPallets, **_orderNbr_, _cookieID_**)

Det finns inga funktionella beroenden förutom nyckelberoendena. Därför är relationerna i BCNF.


## SQL statements
>SQL statements to create all tables, views, stored procedures, and other database elements. (Don’t include statements to create the initial contents of the database.)

För att se de skapade tabellerna, titta på createTables.sql.

## Användarmanual
Lägg till JDBC i referenced libraries efter nedladdning av projektet.
I "Produce Pallets" skriver du in namnet på produkten du vill skapa. Produktens namn, ID och produktions datum står till höger.
I "Block Pallets" skriver du in produktens namn och tidsspannet som du vill blocka i nedanstående rutor.
I "Search Pallets" väljer du först vad du vill söka via. Du skriver sedan in ID/Tid/Kund/Produkt som du vill söka på. För blocked behöver du bara trycka sök så kommer alla blockerade pallar upp. Du får upp en lista över alla pallar som är under sökrestriktionen, och klickar du på ID:t så kommer det upp mer information till höger.
