# Painted Harmony Group DHTMonitor

This project provides some running sample code created by Miles Porter, Sr. Consultant and Co Owner of Painted Harmony
Group Inc.  The purpose of this application is to provide a near-real-time monitor of temperature and humidity.  These
 measurements are captured vi an "IoT" approach that uses DHT22 temperature and humidity sensors that are attached to
 two Rasberry Pi microcontrollers.  The controllers use code written in Python to transmit measurements over WiFi to
 a server running at Amazon (AWS).  The server at Amazon consists of a database (MySQL) and a springboot appliction.
 application provides a RESTful interface for storing temp and humidity readings, and also provide endpoints for
 "getting" the data for reporting purposes.  Finally, the application includes an Angular.js single-page web application
 that is served from the Amazon server, and calls back to the RESTful endpoints for the purposes of data visualization
 and simple analysis.
 
 Please refer to the following diagram for a general overview of the project:
 
https://www.dropbox.com/s/3z99rn7j1x5av12/heat_temp_monitor%20v10.png?dl=0 

 Generally, the project will attempt to following these guidelines where ever possible (and practical.)

1.  Use a TDD based approach as much as possible.
2.  Incorporate testing at the Unit and Integration layer.
3.  Store code in github, and make the code generally available as an open source project.
4.  Provide a method of continuous integration, and possibly continuous delivery.
5.  Demonstrate my abilities to work on a wide range of skills and technology layers.
6.  Lastly, provide me with near-real-time information on the temperature and humidity in my music room in order to
keep my various guitars and basses in the best environment possible.

Layers of the application:

IoT / Rasberry Pi Level.

Middleware level (Java Spring Boot App)

Relational Database Level (MySQL Server)

Application Front End (Angular.js)

Build and Deploy Approach

github
Jenkins on AWS

=====

Stuff I did to make this work.

1.  Install java and intellij on my mac
2.  Install Gradle on my mac
3.  Install lazybones on my mac
4.  Create an spring boot actuator project with lazybones
5.  Write a unit test and integration test and get 'em to pass on my mac
6.  Setup a github repo, and push the code to the repo
7.  Fire up an aws instance and install java and jenkins (see https://blogs.aws.amazon.com/application-management/post/Tx32RHFZHXY6ME1/Set-up-a-build-pipeline-with-Jenkins-and-Amazon-ECS)
8.  Confirm that jenkins is running on port 8080 in aws...
9.  Configure Jenkins to pull from Git, and that the build (gradle) works.
10. Add endpoints to app and confirm that I can test them with Spock.
11.  Add Service and DAO to springboot app.
12.  Get tests workign with mocks for DAOs.
13.  Write DAOs so that they work locally.
14.  Add MySql support (running locally)
15.  Add swagger.  (Swagger docroot is at http://localhost:8888/dhtmonitor/index.html
16.  Install artifactory.
17.  Create post-receive hook for git - now trying to implement this thing in GIT rather than locally.
18.  Removed a MySQL test.  (This is a useless comment, but I put it here to test the build.)
19.  Set up GIT so that when code is checked in, a webhook message is sent to jenkins to trigger a build.
20.  Set up Jenkins so that when it receives a webhook message from git, it compiles the code, runs tests and if everything is "green" it pushes a build to artifactory
21.  Configure my AWS app server instance with a shell script that can pull down the latest app code.
22.  Update the code on the raspberry pi so that it sends not only to beetbotte, but also to the AWS app running in 21 above.
23.  Discover a bug in the code that is resulting in only integer values for the DHT22 being recorded in the database.  This appears to be related to the springboot app because I can recreate the issue through the swagger interface.  Time to write a test.


====

ESP8266 Notes:

In order to get the ESP8266 to work, I followed these steps.

1.  Make sure you have an ESP8266, and not some other old wireless module.
2.  Wire it to a USB FTDI modules, and make sure that you get the wiring right.  Specifically, the GIO pin should NOT be wired to VCC.  This acts as a reset for the module.
  This site was particularly helpful.  http://www.martyncurrey.com/arduino-esp8266/  I used an FTDI module that had an onboard 3.3v VCC.  Make sure not to use 5V on the ESP8266
  I was able to connect to the 8266 using coolterm with COM/Serial settings of 115200,8,N,1.  This document tells the commands:  http://www.pridopia.co.uk/pi-doc/ESP8266ATCommandsSet.pdf
3.  Flash the firmware.
  A.  Download the code here:  http://bbs.espressif.com/viewtopic.php?f=57&t=433
  B.  


