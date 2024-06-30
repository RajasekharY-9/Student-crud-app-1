FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/spring-boot-app.jar spring-boot-app.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-app.jar"]