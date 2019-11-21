# Info Service

## Description
Info service provides useful information about all thet application involved. Also provides a health check in order to validate:
> Space in disk
> Monitor of the applications
> Checking database live

## Setup local env

### Configuration Service
User service reads configuration variables from a config-service.
The information are located at github.com/marcelsamaruga
At the git repository you can find all applications (based on spring.application.name) and its correspondent profile

Configuration service uses the default port 8888

Run the configuration-service before start running the product service

#### How to get information from configuration service?
Spring uses HTTP Get request to retrieve the correct properties/yml file.
To simulate you can call: http://localhost:8888/info-service/default

Where 
- localhost: hostname
- 8888: config-service port at application.yml
- info-service: spring.application.name of the client
- default: profile. It gets the default properties/yml when it has no name on the file.

### Eureka Service
Eureka is the service discovery. It will use spring.application.name to register the info-service.

### Swagger documentation
The endpoints available for info service can be found on the links:
http://localhost:8893/swagger-ui.html
http://localhost:8893/v2/api-docs
http://localhost:8893/actuator
http://localhost:8893/info

Note: if you are using zuul-server the links above should be replace with:
####http://localhost:8000/info-service/

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

#### In a near future:
##### Integrate with CI/CD -> Travis or Jenkins
##### Apply Flyway Migrations to orchestrate database objects
##### Dockenize all the apps on a single docker-compose.yml file