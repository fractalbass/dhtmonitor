package com.phg.dhtmonitor.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by milesporter on 2/19/16.
 */
public class modeltest {

    @Test
    public void dhtTest() {

        Dht dht = new Dht(30,20);
        assertTrue(dht.getHumidity()==20f);
        assertTrue(dht.getTemperature()==30f);
    }
}
