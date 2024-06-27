# ClusteredData Warehouse
Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.
Request logic as following:
- Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
- Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
- System should not import same request twice.
- No rollback allowed, what every rows imported should be saved in DB.
## The technologies used:
- Java 17 oracle
- Meven 4
- SpringBoot 3.3.1
- Lombok
- Docker
- MySql 8.0.28
## Run the code:
- clone the repo -> git@github.com:razanJara/ProgressSoftAssignment.git
# In the terminal 
- run -> mvn clean install -DskipTests
- run -> docker build -t werehouse:latest .
- run docker-compose --build
now the app is running you can go to swagger throw:
Swagger link: http://localhost:8080/swagger-ui/index.html#/

** Note: you must have an mysql image, you can pull it as: " docker pull mysql:latest "
## The API's 
one to do the deal (POST), the other one (GET) to show all the deals, the user can see what are the used Id's so in the POST API, he can enter a unique id in the request
