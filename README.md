# Meal Planner

A simple, web-based meal planning application. Each `Meal` consists of one or more `Dish` which are grouped by `DishType` (e.g. Main, Starch, Vegetable, Dessert).
This way, you can ensure each Meal this week is covered.

## Features:
- Recipe history
- User defined dish types and categories
- Calendar views

## Uses
- [Grails 3.2](http://docs.grails.org/3.2.x/)
- [FullCalendar](https://fullcalendar.io/)


## Installation
The easiest way to install most binaries on OS X is via [Homebrew](http://brew.sh/) and [SDKMAN](http://sdkman.io/).

 - Java 8: Download the latest [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
 - Grails 3.2.10: `$ sdk install grails 3.2.10`
 - Gradle (latest): `$ sdk install gradle`
 - Groovy (latest): `$ sdk install groovy`
 - PostgreSQL: `$ brew install postgresql`


## Usage

Create the database
```
psql -f sql/setup.sql
```

Run the application with Gradle. 
```
./gradlew bootRun
```

## Contributing
View [CONTRIBUTING.md](CONTRIBUTING.md)

## License
TODO: Write license