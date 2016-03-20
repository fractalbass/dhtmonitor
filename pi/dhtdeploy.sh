#This simple file downloads the latest version of the dhtmonitor2.py program to the raspberry pi.
#Note this overwrites the dhtmonitor2.py program on distributed monitors
curl  http://phgci.eastus.cloudapp.azure.com:808/artifactory/libs-snapshot-local/com/phg/dhtmonitor/pi/Dhtmonitor.py
curl  http://phgci.eastus.cloudapp.azure.com:808/artifactory/libs-snapshot-local/com/phg/dhtmonitor/pi/DhtNetwork.py
curl  http://phgci.eastus.cloudapp.azure.com:808/artifactory/libs-snapshot-local/com/phg/dhtmonitor/pi/DhtHardware.py
curl  http://phgci.eastus.cloudapp.azure.com:808/artifactory/libs-snapshot-local/com/phg/dhtmonitor/pi/DhtUtils.py
