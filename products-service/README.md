# Product Service

## Description
Product service provides the ingredients and/or the type of coffee for our beloved mornings

## Setup local env

### Configuration Service
Product service reads configuration variables from a config-service.
The information are located at github.com/marcelsamaruga
At the repository you can find all the application (based on spring.application.name) and its correspondent profile

Configuration service uses the default port 8888

Run the configuration-service before start running the product service

#### How to get information from configuration service?
Spring uses HTTP Get request to retrieve the correct properties/yml file.
To simulate you can call: http://localhost:8888/product-service/default

Where 
- localhost: hostname
- 8888: config-service port at application.yml
- product-service: spring.application.name of the client
- default: profile. It gets the default properties/yml when it has no name on the file.

### Eureka Service
Eureka is the service discovery. It will use spring.application.name to register the product-service.

### Swagger documentation
The endpoints available for the product service can be found on the links:
http://localhost:8891/swagger-ui.html
http://localhost:8891/v2/api-docs

Note: if you are using zuul-server the links above should be replace with:
####http://localhost:8000/scheduling-service/

### Database Connection
To use H2 (in memory database) you need to use an specific profiles indicate at the configuration-service
spring:
 profiles: h2

For a default profile the H2 database is also used

#### Dockenizing with MySQLDB
You are also able to run a docker mysql container running the following command:
docker run --name mysqldb -e MYSQL_DATABASE=db -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_USER=sa -e MYSQL_PASSWORD=sa -p 3306:3306 -it -d mysql

To interact with mysql at docker container:
>docker exec -t -i mysqldb /bin/bash

Don't forget to include at the properties
spring:
 profiles:
  active: mysql
  
## Techniques used on this project
### SpringBoot 2
### Spring Cloud Config Service
### Spring Cloud Eureka
### Spring Cloud Sleuth
### H2 database
### Spring Data JPA
### Swagger2
### Lombok
### Gradle
#### In a near future:
##### Integrate with CI/CD -> Travis or Jenkins
##### Apply Flyway Migrations to orchestrate database objects
##### Dockenize all the apps on a single docker-compose.yml file