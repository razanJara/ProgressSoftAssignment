# Data warehouse for Bloomberg to analyze FX deals
## The technologies used:
- Java 17 oracle
- Meven 4
- SpringBoot 3.3.1
- Lombok
- Docker
- MySql 8.0.28
##
## Run the code:
- clone the repo -> git@github.com:razanJara/ProgressSoftAssignment.git
# In the terminal 
- run -> mvn clean install -DskipTests
- run -> docker build -t werehouse:latest .
- run docker-compose --build
now the app is running you can go to swagger throw:
Swagger link: http://localhost:8080/swagger-ui/index.html#/
##
## The API's 
one to do the deal (POST), the other one (GET) to show all the deals, the user can see what are the used Id's so in the POST API, he can enter a unique id in the request
