# Databasteknik_EDA216
Databasteknik för D &amp;&amp; C programmet för LTH, våren 2017. Inlämningsuppgift/projekt

Johannes Törnblom, dic15jto@student.lu.se, C15

Jenny Martinsson, dic15jma@student.lu.se, C15

## Introduktion
Projektet handlar om att göra ett databassystem åt Krusty Kookies Sweden AB. Vi ska implementera en databas och ett användargränssnitt som hanterar allting som rör produktion, blockering och sökning av pallar.

## Noteringar om vilka krav vi uppfyller/inte uppfyller
#### Produktion
En pall får ett unikt ID, produktens namn och datum vid produktion som registreras i databasen.
Detta görs i "produce pallets".

Vi kan kontrollera hur många pallar som producerats under en viss tid.
Detta görs i "search pallets".
#### Råmaterial
Ingredienserna i lagret uppdateras vid produktion. 
Detta kan ses i konsollen, där amountStorage skrivs ut. Det är 5000 av alla ingredienser från början.

Hur mycket som levererats av ingredienser och när senaste leveransen gjordes. Detta uppfylls inte eftersom vårt program inte har hand om ingredienser på det sättet.

#### Recept
Vi skulle inte implementera recept i vårt program, därför uppfyller vi inte det kravet.

#### Producerade pallar
Blockering av pallar i djupfrys lagring. Sker innan leverans, vilket i vårt program betyder att det finns en kund.
Blockeras i "block pallets". Om en pall redan är levererad så kan den inte blockeras.

Vi kan söka på en pall och få upp information om vad som är på pallen, om pallen är levererad och till vem, och var pallen är. Vi kan också söka på pallar med en viss produkt och pallar producerade under ett visst tidsintervall. Vi kan söka på blockerade pallar.
Detta visas i "search pallets".

Vi har inte information om en pall levererats eller datum och tid av leveransen eftersom vårt program inte sköter leveranser. Kunden kan dock visas.

#### Beställning och produktplanering
VI skulle inte implementera beställningar.

#### Leverans
Vi skulle inte implementera leverans.

## Outline
Vi har använt oss av SQLite för vår databas, och en javaapplication som kommunicerar med vår databas genom JDBC. 
## ER-diagram
Se på följande länk
https://github.com/Treedill/Databasteknik_EDA216/blob/master/ER%20diagram.png?raw=true

## Relationer

customer(**customerName**, address)

orders(**orderNbr**, placedDate, deliveryDate, *customerName*)

pallets(**palletID**, dateProduced, isBlocked, dateDelivered, *cookie*, *orderNbr*)

products(**cookie**)

ingredients(**ingredient**, amountStorage, deliveryDate, deliveryAmount)

recipeItems(amount, **_ingredient_, _cookie_**)

orderItems(nbrPallets, **_orderNbr_, _cookie_**)

Det finns inga funktionella beroenden förutom nyckelberoendena. Därför är relationerna i BCNF.


## SQL statements
>SQL statements to create all tables, views, stored procedures, and other database elements. (Don’t include statements to create the initial contents of the database.)

För att se de skapade tabellerna, titta på createTables.sql.
För annan SQL kod, se "database" i java.

## Användarmanual
Lägg till JDBC i referenced libraries efter nedladdning av projektet. Ta bort eventuell gammal sökväg.
I "Produce Pallets" skriver du in namnet på produkten du vill skapa. Produktens namn, ID och produktionsdatum står till höger. Ingredienserna efter uppdatering skrivs ut med hur mycket som är kvar på lagret.
I "Block Pallets" skriver du in produktens namn och tidsspannet som du vill blocka i nedanstående rutor. Du kan skriva allt från 0 till 2017-07-30 00:00:00 format.
I "Search Pallets" väljer du först vad du vill söka via. Du skriver sedan in ID/Tid/Kund/Produkt som du vill söka på. Sök är "case sensitive" så börja med storbokstav på produkter. Exempel står redan utskrivet. För blocked behöver du bara trycka sök så kommer alla blockerade pallar upp. Du får upp en lista över alla pallar som är under sökrestriktionen, och klickar du på ID:t så kommer det upp mer information till höger.
