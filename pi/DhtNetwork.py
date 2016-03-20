import httplib
import os



if (os.environ.get("dhtenv") == "prod"):
    from beebotte import *
    bbt = BBT('a2d79fbe39c3c231a7b2b84ffb105049', '7211c45499bf9cb6819b1d8a9776fc61e49e0fc9ec66ddaf2b6371e4571d49c7')
    temp_resource   = Resource(bbt, 'rasberrypi', 'temperature')
    humid_resource  = Resource(bbt, 'rasberrypi', 'humidity')

class DhtNetwork:
    def sendBBT(humidity, temperature):
        temp_resource.write(temperature)
        #Send humidity to Beebotte
        humid_resource.write(humidity)


    def sendWeb(webapp, message):
       try:
         print "Send request to {0} monitor name {1}".format(webapp, message)
         headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}
         conn = httplib.HTTPConnection(webapp)
         conn.request("POST",  message, "", headers)
         response = conn.getresponse()
         print "dhtmonitor Server Response {0} {1}".format(response.status, response.reason)
         return True
       except Exception, e:
          print e
          return False