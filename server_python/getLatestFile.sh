#!/bin/bash
#Download file to temp folder
wget http://52.87.226.177:8081/artifactory/libs-snapshot-local/prod-jars/build/libs/dhtmonitor-1.0.0.war -O ./temp/dhtmonitor-1.0.0.war
#Check if file is different than current file
downfile='./temp/dhtmonitor-1.0.0.war'
locfile='./dhtmonitor-1.0.0.war'

#If the file doens't exist, then just make an empty one.
if [ ! -f "$locfile" ]; then
  touch "$locfile"
fi

#Check if the downloaded file is the same as the current.  If it isn't then stop the server, copy the file, and restart the server.
if diff "$downfile" "$locfile" > /dev/null ; 
then
    echo "Files are the same."
else
    echo "Files are different!!!"
    mv ./dhtmonitor-1.0.0.war ./dhtmonitor-1.0.0.war.old
    mv ./temp/dhtmonitor-1.0.0.war .
fi
