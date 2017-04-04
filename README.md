# Databasteknik_EDA216
Databasteknik för D &amp;&amp; C programmet för LTH, våren 2017. Inlämningsuppgift/projekt

Johannes Törnblom, dic15jto@student.lu.se, C15

Jenny Martinsson, dic15jma@student.lu.se, C15

## Introduktion
Projektet handlar om att göra ett databassystem åt Krusty Kookies Sweden AB. Vi ska implementera en databas och ett användargränssnitt som hanterar allting som rör produktion, blockering och sökning av pallar.

## Noteringar om vilka krav vi uppfyller/inte uppfyller
#### Produktion
>A pallet is considered to be produced when the pallet label is read at the entrance to the deep-freeze storage. The pallet number, product name, and date and time of production is registered in the database. The pallet number is unique.

Detta görs i "produce pallets".

>At any time, we must be able to check how many pallets of a product that have been produced during a specific time.

Detta görs i "search pallets".
#### Råmaterial
>When a pallet is produced, the raw materials storage must be updated. We must be able to check the amount in store of each ingredient, and to see when, and how much of, an ingredient was last delivered into storage.

//////Kommer ske. Inte gjort än dock.

#### Recept
Vi skulle inte implementera recept i vårt program, därför uppfyller vi inte det kravet.

#### Producerade pallar
>As we mentioned earlier, pallets in the deep-freeze storage may be blocked. An order to block a pallet will always come before the pallet has been delivered. This is due to the new investments in our laboratory, where the analysis process is completely automated.

Blockeras i "block pallets".

>We must be able to trace each pallet. For instance, we need to see all information about a pallet with a given number (the contents of the pallet, the location of the pallet, if the pallet is delivered and in that case to whom, etc.). We must also be able to see which pallets that contain a certain product and which pallets that have been produced during a certain time interval.

Detta visas i "search pallets".

>Blocked products are of special interest. We need to find out which products that are blocked, and also which pallets that contain a certain blocked product.

Detta visas i "search pallets".

>Finally, we must be able to check which pallets that have been delivered to a given customer, and the date and time of delivery.

/////Delivery visas inte, men det andra gör. Vi kan ju lägga till det om vi vill.

#### Beställning och produktplanering
VI skulle inte implementera beställningar.

#### Leverans
Vi skulle inte implementera leverans.

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

Detta kanske kommer ändras...


## SQL statements
>SQL statements to create all tables, views, stored procedures, and other database elements. (Don’t include statements to create the initial contents of the database.)

För att se de skapade tabellerna, titta på createTables.sql.
För annan SQL kod, se "database" i java.

## Användarmanual
Lägg till JDBC i referenced libraries efter nedladdning av projektet.
I "Produce Pallets" skriver du in namnet på produkten du vill skapa. Produktens namn, ID och produktions datum står till höger.
I "Block Pallets" skriver du in produktens namn och tidsspannet som du vill blocka i nedanstående rutor.
I "Search Pallets" väljer du först vad du vill söka via. Du skriver sedan in ID/Tid/Kund/Produkt som du vill söka på. För blocked behöver du bara trycka sök så kommer alla blockerade pallar upp. Du får upp en lista över alla pallar som är under sökrestriktionen, och klickar du på ID:t så kommer det upp mer information till höger.
