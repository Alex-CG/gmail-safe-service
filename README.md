# gmail-safe-service

# build the application using from the base path (.../gmail-safe-service/):

mvn clean install

# run the application: (If you want to test the application without the Gmail API integration, no credentials needed)

mvn spring-boot:run

# run the application with the Gmail API enabled: (You will need credentials configuration)

mvn spring-boot:run -Dspring-boot.run.arguments=--enableGmailApi=true

# Thoughts:

1. I planned to add a basic MVC endpoint that takes the user the browser and redirect to the OAuth Gmail Login Authentication page
2. After the user has logged in their credentials we should get an access token and send it to the Gmail API Requests, so we can get the data from it.
3. I left some TODOs for future implementations and fixes
4. The application works properly with my own email credentials, if it is possible I would like to do a demo of how this works
   (I have not included the content of any credential files)