FROM maven:3.9.6-amazoncorretto-17 AS build

# copiando a pasta src para dentro de app/src
COPY src /app/src
COPY pom.xml /app

# mudando o diretorioi para /app
WORKDIR /app
RUN mvn clean install

FROM openjdk:8-jre-alpine
# FROM openjdk:17-alpine

# copiando de /target para /app
COPY --from=build /app/target/keyguard-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]