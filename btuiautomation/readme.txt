To run this code please follow the below steps:

1. Replace the last two lines docker compose yml file in the app with
    networks:
      simple-form-net:
        external: true
2. Start the network using below command:
    docker network create simple-form-net
3. Go to the root directory of the Tickets App where the docker compose yml file is located and start the container
    docker compose up
   Verify that server is running by going to http://localhost:4200/
4. Go to the root directory of this project and start the docker container:
    docker compose -f docker-compose.yml up
    Verify that selenium hub is running by going to http://localhost:4444/ui#
5. Once all the containers are running open Intellij IDE (community edition) and open the project using pom.xml file
    Wait for all the dependencies to be downloaded
6.Run all the test in file CreateTicketTest.java
    This file contains UI and API test
7. File Test Cases.txt contains all the test cases


Note: Java code was written using Java version 17.0.10

UI test use page object model contained in package  org.beyondtrust.com.pages;
You can run test in different browser by changing the value in BaseTest - setup method.
This code is setup to run only on remote driver and not on local


Tools Used:
1. Docker
2. Selenium Hub
3. Browser inspect utility (right click on chrome browser and click inspect)
4. Rest Assured Library for API test
5.Test NG to write test cases





