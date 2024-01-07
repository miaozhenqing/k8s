FROM openjdk:8-alpine
WORKDIR /k8s
COPY k8s-0.0.1-SNAPSHOT.jar /k8s/app.jar
#"--spring.profiles.active=server,user"
CMD ["java","-jar","/k8s/app.jar","--spring.config.location=file:./config/"]