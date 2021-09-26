# Offer api technical test

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Offer test [Spring Boot](http://projects.spring.io/spring-boot/) app

Its goal is to expose two endpoint for saving user or getting user's information

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/fr/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.1](https://maven.apache.org)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

To package my app
```shell
mvn clean package
```
To run my app

```
java -jar .\target\offer-0.0.1-SNAPSHOT.jar
```

Or 

```shell
mvn spring-boot:run
```

Or simply run [OfferApplication.java class](src/main/java/com/ktr/offer/OfferApplication.java)

## accessing to h2 database

http://localhost:8080/offer-api/h2-console

Driver Class :  ``` org.h2.Driver```

JDBC URL : ```jdbc:h2:mem:testdb```

User : ```sa```

Password : ```password```


## Swagger

To accessing to this api swagger's documentations and see witch end point and how to use them use the link below   

http://localhost:8080/offer-api/swagger-ui.html

## PiTest

The Pit test  documentation are aviable on [PiTest.md](/PiTest.md)

## More documentation and help

You can also see [HELP.md](HELP.md) for mor documentation and help 

## Post-Man collection

A postman collection was created to automate tests and make them easier to run

Source are on src/test/resources/postman_collections/offertCollection.postman_collection.json 

### I hope you enjoy my work