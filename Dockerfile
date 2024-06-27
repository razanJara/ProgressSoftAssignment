FROM openjdk:17-jdk-oracle
COPY ../target/assignment-0.0.1-SNAPSHOT.jar assignment.jar
COPY src/main/resources/application.properties /app/application.properties
EXPOSE 8080
CMD ["java","-jar","/assignment.jar"]
