FROM openjdk:8-alpine
WORKDIR /docker-test
COPY docker-test-0.0.1-SNAPSHOT.jar /docker-test/app.jar
CMD ["java","-jar","/docker-test/app.jar","--spring.config.location=file:./config/","--spring.profiles.active=server,user"]