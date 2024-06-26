FROM openjdk:17-jdk-oracle
COPY ../target/assignment-0.0.1-SNAPSHOT.jar assignment.jar
EXPOSE 8080
CMD ["java","-jar","/assignment.jar"]
