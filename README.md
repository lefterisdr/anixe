# ANIXE assignment

[![Build Status](https://travis-ci.org/lefterisdr/anixe.svg?branch=master)](https://travis-ci.org/lefterisdr/anixe/)


## Project creation
In order to get the project from [Github](http://www.github.com), run the following:
```
git clone https://github.com/lefterisdr/anixe.git
```


## Database installation
The project supports MySQL as a full-blown database. In order to use the project with MySQL:
-  Download and install [MySQL](http://www.mysql.org)
-  In order to create the tables and load them with some data, run the SQL script located in 
```sh 
src/main/resources/db/create_and_populate.sql
```


## Environment
The project requires Java 1.8+ and Maven 3


## Tests
The tests are executed against an instance of H2 in-memory database. In order to execute the tests, go to the root folder of the Java project and execute the following:
```sh
mvn test
```
> You need to have Java and Maven in your environment's path.


## Producing the executable JAR file
In order to produce the JAR file of the application execute the following from the project's root folder:
```sh
mvn clean install
```
> The JAR will be generated in folder "target"


## Running
In order to run the project you need three things:
- the produced JAR file
- the configuration files: application.properties and log4j.xml
- a folder named "logs" that will contain the application logs

Once those are in place, run the application as follows:
```
java -jar assignment-1.0.jar
```
> Make sure to update application.properties with the specific information of the local MySQL instance 
> and adjust accordingly the log levels in both application.properties and log4j.xml


## Documentation (Swagger)
The REST APIs are documented at http://localhost:8082/swagger-ui.html


## Graceful shutdown
In order to terminate the application gracefully execute:
```
curl -X POST http://localhost:8082/actuator/shutdown
```