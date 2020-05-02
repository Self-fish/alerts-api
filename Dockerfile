FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/alerts-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017/mongodb-test","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]