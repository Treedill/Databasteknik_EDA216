# Databasteknik_EDA216
Databasteknik för D &amp;&amp; C programmet för LTH, våren 2017. Inlämningsuppgift/projekt

Johannes Törnblom, dic15jto@student.lu.se, C15

Jenny Martinsson, dic15jma@student.lu.se, C15

## Introduktion
Projektet handlar om att göra ett databassystem åt Krusty Kookies Sweden AB. Vi ska implementera en databas och ett användargränssnitt som hanterar allting som rör produktion, blockering och sökning av "pallets".

## Noteringar om vilka krav vi uppfyller/inte uppfyller

## Outline

## ER-diagram

## Relationer

customers(**ssn**, customerName, address)

orders(**orderNbr**, placedDate, deliveryDate, *ssn*)

pallets(**palletID**, dateProduced, isBlocked, dateDelivered, *cookieID*, *orderNbr*)

products(**cookieID**, cookie)

ingredients(**ingredientID**, amountStorage, deliveryDate, deliveryAmount)

recipeItems(amount, **_ingredientID_, _cookieID_**)

orderItems(nbrPallets, **_orderNbr_, _cookieID_**)

Det finns inga funktionella beroenden förutom nyckelberoendena. Därför är relationerna i BCNF.


## SQL statements to create all tables, views, stored procedures, and other database elements. (Don’t include statements to create the initial contents of the database.)

## Användarmanual
