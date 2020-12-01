FROM maven:3.6.3-jdk-11 AS maven
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn package

FROM openjdk:11
WORKDIR /app
COPY --from=maven app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]