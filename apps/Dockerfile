FROM gradle:6.8.3-jdk11-hotspot AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim

VOLUME /redis-service

EXPOSE 8080

RUN mkdir /app

ARG JAR_FILE_NAME=/app/app.jar

COPY --from=build /home/gradle/src/build/libs/*.jar ${JAR_FILE_NAME}

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]