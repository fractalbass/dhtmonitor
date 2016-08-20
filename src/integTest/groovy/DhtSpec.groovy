import com.phg.dhtmonitor.DhtMonitorApplication
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Ignore
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = DhtMonitorApplication)
@WebAppConfiguration
@IntegrationTest
class DhtSpec extends Specification {

    //  Proper integration tests should have backend DAO calls mocked.

    @Value('${local.server.port}')
    int port;


    @Ignore
    def "It should have a valid endpoint"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/hello")
        assert(resp.status==200)
    }

    @Ignore
    // you should be able to add a point
    def "you should be able to add a point"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.post(path : "/dhtmonitor/temperature/10/humidity/10/sensor/1")
        assert(resp.status==201)
    }

    @Ignore
    // you should be able to get the last N points
    def "you should be able to get the last N points"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/seconds/5")
        assert(resp.status==200)
    }

    @Ignore
    // you should be able to get the last N points
    def "you should be able to get the last N points for sensor x"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/sensor/1/seconds/5")
        assert(resp.status==200)
    }

    @Ignore
    // you should be able to get all points going back N seconds
    def "you should be able to get all points going back N readings"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/count/5")
        assert(resp.status==200)
    }

    @Ignore
    // you should be able to get all points going back N seconds
    def "you should be able to get all points for sensor x going back N readings"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/sensor/1/count/5")
        assert(resp.status==200)
    }

    @Ignore
    // Will need to figure out a better way to mock this test for CI.
    def "you should be able to save a point with digits."() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.post(path : "/dhtmonitor/temperature/10.555/humidity/10.123/sensor/1")
        assert(resp.status==201)
        def resp2 = client.get(path : "/dhtmonitor/readings/sensor/1/count/1");
        assert(resp2.status==200)
        assert(resp2.responseData[0].humidity==10.123)
        assert(resp2.responseData[0].temperature==10.555)
    }
}