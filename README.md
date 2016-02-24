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
 
 http://www.gliffy.com/go/publish/image/9944981/L.png
 
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
17.  Create post-receive hook for git.
18.  Removed a MySQL test.  (This is a useless comment, but I put it here to test the build.)
