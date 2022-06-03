# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 17, try this
FROM openjdk:17
FROM postgres:latest

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/*.jar

# cd /opt/app
#WORKDIR /opt/app

# cp target/*.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]