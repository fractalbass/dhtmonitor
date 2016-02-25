#!/usr/bin/env python

############################################################
# This code uses the Beebotte API, you must have an account.
# You can register here: http://beebotte.com/register
############################################################

import time
import Adafruit_DHT
from beebotte import *
import datetime
import RPi.GPIO as GPIO ## Import GPIO Library
import time ## Import 'time' library.  Allows us to use 'sleep'

### Replace API_KEY and SECRET_KEY with those of your account
bbt = BBT('a2d79fbe39c3c231a7b2b84ffb105049', '7211c45499bf9cb6819b1d8a9776fc61e49e0fc9ec66ddaf2b6371e4571d49c7')
 
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
    GPIO.cleanup()
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
          sendAWS(temperature, humidity)
          Blink(int(iterations),float(speed))

        except Exception as e:
          ## Process exception here
          s = str(e)
          print "Error while writing to Beebotte {0}".format(s)
    else:
        print "Failed to get reading. Try again!"

## Define function named Blink()
def Blink(numTimes, speed):
    for i in range(0,numTimes): ## Run loop numTimes
        GPIO.output(11, True) ## Turn on GPIO pin 7
        time.sleep(speed) ## Wait
        GPIO.output(11, False) ## Switch off GPIO pin 7
        time.sleep(speed) ## Wait
    GPIO.cleanup()

def sendAWS(temperature, humidity):
   try:
     headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}
     conn = httplib.HTTPConnection("54.172.123.149:8888")
     conn.request("POST", "/dhtmonitor/temperature/{0}/humidity/{1}".format(temperature, humidity), "", headers)
     response = conn.getresponse()
     print "AWS Server Response {0} {1}".format(response.status, response.reason)
   except Exception, e:
      print e

run()
