FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src src
RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/config-server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
