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

