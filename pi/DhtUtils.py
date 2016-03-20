class DhtUtils:

    def GetUrl(self, sensor, temperature, humidity):
        return  "/dhtmonitor/temperature/{0}/humidity/{1}/sensor/{2}".format(temperature, humidity, sensor)

