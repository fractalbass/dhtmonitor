import unittest
import DhtUtils

class DhtmonitorUnitTest(unittest.TestCase):

    def test(self):
        self.assertTrue(True)

    def test_format_url(self):
        utils = DhtUtils.DhtUtils()
        req = utils.GetUrl("sensor1", 20.20, 10.10)
        self.assertEqual("/dhtmonitor/temperature/20.2/humidity/10.1/sensor/sensor1", req)

if __name__ == '__main__':
    unittest.main()
