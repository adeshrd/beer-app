# beer-app
A simple beer app that randomly displays beers to the user

---

### Demo

![screendemo.gif](screendemo.gif)


### How to run app

1. ./gradlew build
2. java -jar build/lib/beer.jar


### How to run tests

1. ./gradlew test

### Information
 - Shows beer data along with images using base64 encoding
 - Passes current beer name so always a new random beer is fetched 
 - Two ways to fetch random beer defined in BeerService.java
 - Unit tests and integration tests written in src/test
 
 
### Notes

 - The images used in this app are base64 encoded and saved in the database. 
 Ideally they should be stored in a object storage like S3 and served over CDN
 - The project uses lombok so you will need annotation processing enabled in your IDE
 - The project currently runs on a in memory H2 database.
 You can easily switch to a MySQL database by uncommenting the corresponding properties in application.properties 
----


