# Elevator Pitch Platform
## About
---
Project was created for WSEI Design Thinking Hub science club. It will be used for realize competition called "Elevator Pitch". Application has two dedicated profiles, one is dedicated for developpers, second is for production environment. 
 
## Features
 ---
- Register startup team 
- Send email to support with custom user message and his name 
- Confirm account via email with verification link
- Login to user panel 
- Insert startup idea
- Edit user data 
- Delete account
- Change password
- Resend email with verification link
---

#### Admin features
- Get all registered teams
- Edit own password
- Send the with email to each participant written in HTML
- Review Idea
---

## Usage
---
>Mostly features depends on logged user . 
>Here I will provide which user has got feature deppending on his authority .
 
| Endpoint | Action with descritpion |Authority|
|------|------|-----|
|/register|Endpoint to register team with validation|Anybody|
|/resendEmail|Send again email to requested mail|Anybody|
|/contact-form|Provide admin email with message|Anybody|
|/login|Login with LoginCredenitials JSON|Anybody|
|/editUserData|User will edit his own data|User|
|/getUserData|Get user data from backend|Admin,User,Test|
|/deleteTeam|Delete logged team|User|
|/changePassword|Change password endpoint |Admin,User|
|/admin/annouce|Admin will send annouce message to all teams |Admin|
|/admin/setQualifcationStatusAndComment/{id}|Place where we can put review |Admin|
|/admin/getTeams|Get all present teams from backend|Admin|
> *` Warring`*
>Program should be configured correctly before launch with correct environment variables .
>Watch [Installation](#Configure-enviroment-variables) part .

## Tech stack
---
- [Spring framework] - It iss a set of production ready for user in production environment
- [Spring Boot] - Spring Boot eazes creating stand-alone, production-grade Spring based on Applications that you can "just run"
- [Spring MVC] - Spring Web MVC is the original web framework built on the Servlet API and has been included in the Spring Framework from the very beginning
- [Spring Data JPA] - Spring project that provides API for map the entities. Under simple API works Hibernate with ORM 
- [Spring Security] - Spring Security is a framework that provides authentication and authorization
- [Thymeleaf] - is a server-side Java template engine for both web and standalone environments
- [Java Mail API] - Java Library for sending emails from java application. It supports emails in HTML
- [Lombock] - Library to reduce boilerplate code
- [H2 Database] - In memory database mostly used for developer needs
- [MySQL] - It is the most popular SQL server
- [Liquibase] - It is opensource library for DB migrations which support SQL, XML and other formats 
- [Jakarta Bean Validation] - is a pack of annotation with constraints and validation parameters
- [JWT] - Json Web Token defines a compact and self-contained way for securely transmitting information between parties as a JSON object
## Installation
---
Application requires Java 16 + to run and in production profile MySQL server additionally.
 
If you want to compile this project you have to have JDK with java 16 + and enter the package and then run the commands below. 
 
```sh
mvn clean
mvn compile
mvn package
```
That commands will create <NAME_FILE>.jar file which is ready to deploy to server or run by comand : 
```
java -jar <NAME_FILE>.jar
```
It will run our spring application . Following command will run it in the background .
```
java -jar <NAME_FILE>.jar &
```
We can run program some custom environment variables. Just replace <VARIABLE> with name of variable xyz value. We can use it as much as we want to. 
```
java -jar -D<VARIABLE>=xyz <NAME_FILE>.jar
```
### Configure enviroment variables
---
> Our application has to be configured before launch .
> Here I will provide you some informations what is nessesary to be configured.
 
In file application.properities you have to provide informations: 
```
frontEndLink=
emailSupport=
jwt.expirationTime=
jwt.secret=
spring.mail.password=
spring.mail.username=
admin.password=
admin.email=
testUser.password=
testUser.email=
spring.profiles.active=
```
- frontEndLink - fill with URI to frontend 
- emailSupport - fill with email to whom will be going mails from contactform 
- jwt.expirationTime - fill with number of seconds how many seconds will be JWT Token valid 
- jwt.secret - fill with generated secret KEY (Array of characters)
- spring.mail.password - fill with password to emailsender 
- spring.mail.username - fill with username of emailsender
- admin.password - fill with admin password ( ADMIN LOGIN CREDENITIAL )
- admin.email - fill with admin email (ADMIN LOGIN CREDENITIAL) 
- That same do for TESTUSER
- spring.profiles.active= fill with dev ( developer mode ) or prod (production mode)

If you want to configure for example database URL you will have to edit application-??.yml file depends on profile which will be active. 

## Docker
---
Application is easy to build with docker. I provided dedicated DockerFile so everything is done expect build image. `Application will run in PRODUCTION MODE by defalut here.`
 
By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.
 
You have to compile application before use this command .
```sh
docker build -t .
```
 
This will create the app image and pull in the necessary dependencies.
 
Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):
 
```sh
docker run -d -p 8000:8080 --restart=always --cap-add=SYS_ADMIN --name=<YOUR_NAME>
```
## Demo account
---
At present application is avaliable at address 
[http://dth.wsei.edu.pl/](http://dth.wsei.edu.pl/)
To test that application is running I have created test user which much reduced authorities than normal user. 
Login credenitials 
```
Login : test@test.pl
Password : test
```

## Inspiration
JWT authentication is based on [this](https://nullpointerexception.pl/spring-security-i-json-web-token/) article . 
Besides that I have created alone my own implementation of UserDetailsService , rebuilt configure method dedicated to my ussage and mutch more . 

[JWT]: <https://jwt.io/introduction>
[Spring framework]: <https://spring.io/projects/spring-framework>
[Spring Boot]:<https://spring.io/projects/spring-boot>
[Spring MVC]:<https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html>
[Spring Data JPA]:<https://spring.io/projects/spring-data-jpa>
[Spring Security]:<https://spring.io/projects/spring-security>
[Jakarta Bean Validation]:<https://beanvalidation.org/2.0/spec/>
[Thymeleaf]:<https://www.thymeleaf.org/>
[Java Mail API]:<https://www.oracle.com/java/technologies/javamail-api.html>
[Lombock]:<https://projectlombok.org/>
[H2 Database]:<http://h2database.com/html/main.html>
[MySQL]:<https://www.mysql.com/>
[Liquibase]:<https://www.liquibase.org/>
