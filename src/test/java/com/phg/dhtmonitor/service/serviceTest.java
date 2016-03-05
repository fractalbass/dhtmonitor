package com.phg.dhtmonitor.model;

import static org.junit.Assert.*;

import com.phg.dhtmonitor.dao.MySqlDao;
import com.phg.dhtmonitor.service.DhtService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Created by milesporter on 2/19/16.
 */
public class serviceTest {

    DhtService dhtService = new DhtService();


    @Before
    public void setup() {


    }

    @Test
    public void testServicePut() {
        MySqlDao mockDao = mock(MySqlDao.class);
        when(mockDao.saveMeasurement(any(Measurement.class))).thenReturn(true);
        when(mockDao.saveDht(any(Dht.class))).thenReturn(true);
        dhtService.setDao(mockDao);
        assertTrue(dhtService.SaveDHT("sensor1", 10, 10));
    }

    @Test
    public void testServiceGetCount() {
        MySqlDao mockDao = mock(MySqlDao.class);
        when(mockDao.getLastByCount(anyInt())).thenReturn(getMockData());
        dhtService.setDao(mockDao);
        assertTrue(dhtService.getLastByCount(6).size()==6);
    }

    @Test
    public void testServiceGetSeconds() {
        MySqlDao mockDao = mock(MySqlDao.class);
        when(mockDao.getLastBySeconds(anyInt())).thenReturn(getMockData());
        dhtService.setDao(mockDao);
        assertTrue(dhtService.getLastBySeconds(60).size()==6);

    }

    private ArrayList<Measurement> getMockData() {
        ArrayList<Measurement> l = new ArrayList<>();
        l.add(new Measurement("Server1", "temperature",10));
        l.add(new Measurement("Server1", "humidity", 20));
        l.add(new Measurement("Server1", "temperature", 30));
        l.add(new Measurement("Server1", "humidity", 40));
        l.add(new Measurement("Server1", "temperature", 50));
        l.add(new Measurement("Server1", "humidity", 50));
        return l;

    }
}