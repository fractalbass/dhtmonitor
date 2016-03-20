#This simple file downloads the latest version of the dhtmonitor2.py program to the raspberry pi.
#Note this overwrites the dhtmonitor2.py program on distributed monitors
curl  http://phgci.eastus.cloudapp.azure.com:8081/artifactory/libs-snapshot-local/com/phg/dhtmonitor/pi/dhtmonitor.tar > dhtmonitor.tar
tar -xvf dhtmonitor.tar