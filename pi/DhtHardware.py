import datetime
import os

if (os.environ.get("dhtenv") != "dev"):
    import RPi.GPIO as GPIO ## Import GPIO Library
    import Adafruit_DHT

class DhtHardware:

    outPin = 11
    inPin = 4

    def initialize(self):
        GPIO.setwarnings(False)
        GPIO.cleanup()
        GPIO.setmode(GPIO.BOARD) ## Use BOARD pin numbering
        GPIO.setup(self.outPin, GPIO.OUT) ## Setup GPIO pin 11 to OUT

    def readDhtValues(self):
        humidity, temperature =  Adafruit_DHT.read_retry( Adafruit_DHT.DHT22, self.inPin )
        return (humidity, temperature)