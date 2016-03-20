import unittest
from DhtmonitorApp import DhtmonitorApp
from DhtNetwork import DhtNetwork
from DhtHardware import DhtHardware
import logging
import sys
from mock import MagicMock

class DhtmonitorIntegrationTest(unittest.TestCase):

    def test(self):
        self.assertTrue(True)

    def test_main_function(self):
        hardware = DhtHardware()
        hardware.readDhtValues = MagicMock(return_value=(10.1, 20.2))
        hardware.initialize = MagicMock(return_value=True)
        network = DhtNetwork()
        network.sendWeb = MagicMock(return_values=True)
        network.sendBBT = MagicMock(return_values=True)
        dht = DhtmonitorApp()
        dht.hardware = hardware
        dht.network = network
        dht.execute( "webapp", "sensor")
        network.sendWeb.assert_called_with("webapp", "/dhtmonitor/temperature/20.2/humidity/10.1/sensor/sensor" )
        network.sendBBT.assert_called_with(10.1, 20.2)


if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    logger = logging.getLogger(__name__)
    stream_handler = logging.StreamHandler(sys.stdout)
    logger.addHandler(stream_handler)
    logger.info('Started logging')
    unittest.main()