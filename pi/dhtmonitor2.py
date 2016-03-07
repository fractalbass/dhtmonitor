#!/usr/bin/env python

############################################################
# This code uses the Beebotte API, you must have an account.
# You can register here: http://beebotte.com/register
############################################################
import sys
import getopt
import httplib
import time
import Adafruit_DHT
from beebotte import *
import datetime
import RPi.GPIO as GPIO ## Import GPIO Library
import time ## Import 'time' library.  Allows us to use 'sleep'
GPIO.cleanup()
### Replace API_KEY and SECRET_KEY with those of your account
bbt = BBT('a2d79fbe39c3c231a7b2b84ffb105049', '7211c45499bf9cb6819b1d8a9776fc61e49e0fc9ec66ddaf2b6371e4571d49c7')
webapp = ""
monitorName = ""
period = 60 ## Sensor data reporting period (1 minute)
pin = 4 ## Assuming the DHT11 sensor is connected to GPIO pin number 4

GPIO.setmode(GPIO.BOARD) ## Use BOARD pin numbering
GPIO.setup(11, GPIO.OUT) ## Setup GPIO pin 11 to OUT

### Change channel name and resource names as suits you
temp_resource   = Resource(bbt, 'rasberrypi', 'temperature')
humid_resource  = Resource(bbt, 'rasberrypi', 'humidity')
iterations = 445
speed = 1

def run():
    # Turn led off.  We'll turn it back on if everything works.
    GPIO.output(11, False) ## Turn on GPIO pin 7
    print "Arguments passed: {0:s} ".format(sys.argv[1])
    webapp = sys.argv[1]
    monitorName = sys.argv[2]
    ts = time.time()
    st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d %H:%M:%S')
    humidity, temperature = Adafruit_DHT.read_retry( Adafruit_DHT.DHT22, pin )
    if humidity is not None and temperature is not None:
        print "{2:s} Temp={0:f}*C  Humidity={1:f}%".format(temperature, humidity,st)
        try:
          #Send temperature to Beebotte
          temp_resource.write(temperature)
          #Send humidity to Beebotte
          humid_resource.write(humidity)
          #Send it all to aws
          sendWeb(webapp, monitorName, temperature, humidity)
          #  Turn light on to indicate success 
          GPIO.output(11, True) ## Turn on GPIO pin 7

        except Exception as e:
          ## Process exception here
          s = str(e)
          print "Error while writing to Beebotte {0}".format(s)
    else:
        print "Failed to get reading. Try again!"

def sendWeb(webapp, monitorName, temperature, humidity):
   try:
     headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}
     conn = httplib.HTTPConnection(webapp)
     conn.request("POST", "/dhtmonitor/temperature/{0}/humidity/{1}/sensor/{2}".format(temperature, humidity, monitorName), "", headers)
     response = conn.getresponse()
     print "dhtmonitor Server Response {0} {1}".format(response.status, response.reason)
   except Exception, e:
      print e

run()

