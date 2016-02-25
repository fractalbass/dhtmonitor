#!/usr/bin/env python

import urllib, httplib

def run():
   print "Running"
   sendAWS(10,17)
   print "Wha?"


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