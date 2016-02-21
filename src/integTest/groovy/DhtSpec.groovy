import com.phg.dhtmonitor.DhtMonitorApplication
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = DhtMonitorApplication)
@WebAppConfiguration
@IntegrationTest
class DhtSpec extends Specification {

    @Value('${local.server.port}')
    int port;

    def "It should have a valid endpoint"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/hello")
        assert(resp.status==200)
    }

    // you should be able to add a point
    def "you should be able to add a point"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.post(path : "/dhtmonitor/temperature/10/humidity/10")
        assert(resp.status==200)
    }

    // you should be able to get the last N points
    def "you should be able to get the last N points"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/seconds/5")
        assert(resp.status==200)
    }

    // you should be able to get all points going back N seconds
    def "you should be able to get all points going back N seconds"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/readings/count/5")
        assert(resp.status==200)
    }
}