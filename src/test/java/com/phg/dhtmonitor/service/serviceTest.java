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
        when(mockDao.saveDht(any(Dht.class))).thenReturn(true);
        dhtService.setDao(mockDao);
        assertTrue(dhtService.SaveDHT(10, 10));
    }

    @Test
    public void testServiceGetCount() {
        MySqlDao mockDao = mock(MySqlDao.class);
        when(mockDao.getLastByCount(anyInt())).thenReturn(getMockData());
        dhtService.setDao(mockDao);
        assertTrue(dhtService.getLastByCount(5).size()==5);
    }

    @Test
    public void testServiceGetSeconds() {
        MySqlDao mockDao = mock(MySqlDao.class);
        when(mockDao.getLastBySeconds(anyInt())).thenReturn(getMockData());
        dhtService.setDao(mockDao);
        assertTrue(dhtService.getLastBySeconds(60).size()==5);

    }

    private ArrayList<Dht> getMockData() {
        ArrayList<Dht> l = new ArrayList<>();
        l.add(new Dht(10,10));
        l.add(new Dht(20,20));
        l.add(new Dht(30,30));
        l.add(new Dht(40,40));
        l.add(new Dht(50,50));
        return l;

    }
}