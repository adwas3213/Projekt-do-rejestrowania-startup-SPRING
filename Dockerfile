FROM openjdk:17.0.2-slim-buster
ADD target/registerStartUpProject-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod registerStartUpProject-0.0.1-SNAPSHOT.jar
