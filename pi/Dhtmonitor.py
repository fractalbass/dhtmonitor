#!/usr/bin/env python
import sys
import DhtHardware
import DhtNetwork
import DhtUtils
import DhtmonitorApp

webapp = ""
monitorName = ""


def run():
    print "Arguments passed: {0} ".format(sys.argv)
    webapp = sys.argv[1]
    monitorName = sys.argv[2]
    d = DhtmonitorApp.DhtmonitorApp()
    d.execute(webapp, monitorName)

run()

