import DhtNetwork
import DhtUtils
import DhtHardware

class DhtmonitorApp:

    hardware = DhtHardware.DhtHardware()
    network = DhtNetwork.DhtNetwork()
    utils = DhtUtils.DhtUtils()

    def execute(self, webapp, monitorName):

        self.hardware.initialize()

        humidity, temperature = self.hardware.readDhtValues()
        if humidity is not None and temperature is not None:
            print "Reading taken.  Sending values."
            url = self.utils.GetUrl(monitorName, temperature, humidity)
            self.network.sendWeb(webapp, url)
            self.network.sendBBT(humidity, temperature)
        else:
            print "Failed to get reading. Try again!"