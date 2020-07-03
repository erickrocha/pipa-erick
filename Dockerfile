FROM openjdk:8-jdk-alpine
MAINTAINER Erick Rocha
WORKDIR /opt/app
RUN addgroup -S erick && adduser -S erick -G erick
USER erick:erick
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]