### palindrome checker
Spring Boot Application to to accept a username and text value and return an indicator whether
that value is a palindrome - A palindrome is a word, number, phrase, or other sequence of
characters which reads the same backward as forward, such as madam or kayak.

### Overview
1. Validate the input
2. Cache previously processed values.
3. Each processed value written to a permanent storage solution, file system in this case

### REST API
The application is based on REST endpoints which has GET & POST methods
```
* GET /api/check-palindrome/{request}
```

### Implementation details

This is a Spring Boot application using Java 11 features. The application is built using Maven. 

**How to run this application:**
- Package the compiled code to distributable format, such as a JAR**
    mvn package 

- Run the Jar locally**
   java -jar target\palindrome-1.0-SNAPSHOT.jar ** 

### Integration test
Run this test to spin the actual spring boot application
 
### Run Local
Run the below commands from the command line:

```
git clone https://github.com/sprathipati85/palindrome.git
cd palindrome
./mvnw spring-boot:run
```

The command starts the Spring Boot application which includes REST API

```
REST API Endpoint - http://localhost:8080

* GET /api/check-palindrome/madam
```

### Cache
SimpleCacheManager is used to cache data. 

### Assumptions

### Future work
Extend the file saving to use database
Handle duplicates
Usage of Redis or production grade cache
