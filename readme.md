# Price Service

Service that provides endpoint to get the price to apply for a specific product and brand in a given date and hour. 

### Tech stack
- Java 11
- Spring Boot Plugin 2.7.6
- H2 Database 2.1.214
- Gradle 8.4

### Build

The application can be built from command line with:
```shell
$ ./gradlew build
```

### Run

After build the app, a .war file will be created in build/libs folder.
Once you are placed in build/libs folder, the application can be launched from command line with:
```shell
 java -jar napptilus-test-1.0.0.war
```

### Swagger

The endpoint exposed in this service can be tested via swagger in the following url, after the app was started:
http://localhost:8080/swagger-ui/index.html.