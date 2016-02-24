package com.phg.dhtmonitor.com.phg.dhtmonitor.dao;

import com.phg.dhtmonitor.dao.MySqlDao;
import com.phg.dhtmonitor.model.Dht;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by milesporter on 2/21/16.
 */
public class MySqlDaoTest {

    @Ignore
    @Test
    public void databaseTest() {
        MySqlDao msd = new MySqlDao();
        Dht dht = new Dht(10,10);
        boolean result = msd.saveDht(dht);
        assertTrue(result);
    }
}
