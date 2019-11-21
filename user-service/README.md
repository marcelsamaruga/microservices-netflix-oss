# User Service

## Description
User service provides the participants of the coffee group

## Setup local env

### Configuration Service
User service reads configuration variables from a config-service.
The information are located at github.com/marcelsamaruga
At the git repository you can find all applications (based on spring.application.name) and its correspondent profile

Configuration service uses the default port 8888

Run the configuration-service before start running the product service

#### How to get information from configuration service?
Spring uses HTTP Get request to retrieve the correct properties/yml file.
To simulate you can call: http://localhost:8888/user-service/default

Where 
- localhost: hostname
- 8888: config-service port at application.yml
- product-service: spring.application.name of the client
- default: profile. It gets the default properties/yml when it has no name on the file.

### Eureka Service
Eureka is the service discovery. It will use spring.application.name to register the product-service.

### Swagger documentation
The endpoints available for the product service can be found on the links:
http://localhost:8892/user-service/swagger-ui.html
http://localhost:8892/v2/api-docs

## Techniques used on this project
### SpringBoot 2
### Spring Cloud Config Service
### Spring Cloud Eureka
### Spring Cloud Sleuth
### Spring Actuator
### Spring Hateaos
### H2 database
### Spring Data JPA
### Swagger2
### Lombok
### Gradle
### In a near future:
#### Integrate with CI/CD -> Travis or Heroku
#### To create a docker container for the service and move to MySQL database
#### Apply Flyway Migrations to orchestrate database objects