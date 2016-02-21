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
@IntegrationTest("server.port:8888")
class PrimeSpec extends Specification {

    @Value('${local.server.port}')
    int port;

    def "It should have a valid endpoint"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor/")
        def resp = client.get(path : "dht")
        assert(resp.status==200)
    }
}