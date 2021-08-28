# gmail-safe-service

# build the application using from the base path (.../gmail-safe-service/):

mvn clean install

# run the application:
(The application is able to work and handle the backup info but not get the data from the Gmail API. You can execute the POST /v0.1/backups and GET /v0.1/backups endpoints)

mvn spring-boot:run

# run the application with the Gmail API enabled:
(Currently it is not supported so, even if the application starts it will get a 401 error)

mvn spring-boot:run -Dspring-boot.run.arguments=--enableGmailApi=true

# Thoughts:

1. I planned to add a basic MVC endpoint that takes the user the browser and redirect to the Gmail Login Authentication page
2. After the user has logged in their credentials we should get a jwt token and send it to the Gmail API Requests, so we can get the data from it.
3. I left some TODOs for a future implementation and fix
4. Tests written for the GET and POST endpoints and some services, utils, etc.