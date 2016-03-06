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

And this blog for a detailed look at what I have been doing.

http://iotuncensored.blogspot.com/

March 5, 2016
