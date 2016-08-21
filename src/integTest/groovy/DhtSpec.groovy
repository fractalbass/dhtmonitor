import com.mysql.jdbc.exceptions.MySQLInvalidAuthorizationSpecException
import com.phg.dhtmonitor.DhtMonitorApplication
import com.phg.dhtmonitor.dao.MySqlDao
import com.phg.dhtmonitor.model.Measurement
import com.phg.dhtmonitor.service.DhtService
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    DhtService dhtService

    MySqlDao mySqlDao

    @Value('${local.server.port}')
    int port;


    def setup() {
        mySqlDao  = Mock(MySqlDao)
        dhtService.dao = mySqlDao
    }

    def "It should have a valid endpoint"() {
        expect:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        def resp = client.get(path : "/dhtmonitor/hello")
        assert(resp.status==200)
    }

    // you should be able to add a point
    def "you should be able to add a point"() {
        given:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        when:
        def resp = client.post(path : "/dhtmonitor/temperature/10/humidity/10/sensor/1")
        then:
        assert(resp.status==201)
        1 * mySqlDao.saveMeasurement( _ )
    }

    // you should be able to get the last N points
    def "you should be able to get the last N points"() {
        given:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        when:
        def resp = client.get(path : "/dhtmonitor/readings/seconds/5")
        then:
        assert(resp.status==200)
        1 * mySqlDao.getLastBySeconds( _ ) >> new ArrayList<Measurement>()
    }

    // you should be able to get the last N points
    def "you should be able to get the last N points for sensor x"() {
        given:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        when:
        def resp = client.get(path : "/dhtmonitor/readings/sensor/1/seconds/5")
        then:
        assert(resp.status==200)
        1 * mySqlDao.getLastBySecondsandServer( _ , _ ) >> new ArrayList<Measurement>()
    }

    // you should be able to get all points going back N seconds
    def "you should be able to get all points going back N readings"() {
        given:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        when:
        def resp = client.get(path : "/dhtmonitor/readings/count/5")
        then:
        assert(resp.status==200)
        1 * mySqlDao.getLastByCount( _ ) >> new ArrayList<Measurement>()
    }

    // you should be able to get all points going back N seconds
    def "you should be able to get all points for sensor x going back N readings"() {
        given:
        def client = new RESTClient("http://localhost:8888/dhtmonitor")
        when:
        def resp = client.get(path : "/dhtmonitor/readings/sensor/1/count/5")
        then:
        assert(resp.status==200)
        1 * mySqlDao.getLastByCountAndServer( _ , _ ) >> new ArrayList<Measurement>()
    }
}