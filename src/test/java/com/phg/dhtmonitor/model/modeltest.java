package com.phg.dhtmonitor.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by milesporter on 2/19/16.
 */
public class modeltest {

    @Test
    public void dhtTest() {

        Dht dht = new Dht();
        dht.setHumidity(30);
        dht.setTemperature(20);
        assertTrue(dht.getHumidity()==30f);
        assertTrue(dht.getTemperature()==20f);
    }
}
