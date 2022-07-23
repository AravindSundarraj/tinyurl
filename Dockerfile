# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 17, try this
FROM openjdk:17-oracle

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/*.jar

# cd /opt/app
#WORKDIR /opt/app

# cp target/*.jar /opt/app/app.jar
COPY ${JAR_FILE} arav-tinyurl.jar
EXPOSE 8082

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","arav-tinyurl.jar"]


# docker build . -t app1
# docker image rm -f app1
# docker container ls

#docker inspect 78c8b35a7ae0 | grep IPAddress

# docker run -p 8082:8082 app1