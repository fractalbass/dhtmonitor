package com.phg.dhtmonitor.model;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void SensorGraphData() {
        ArrayList<SensorGraphData> sdgs = new ArrayList<>();

        for (int i=0;i<3;i++) {
            SensorGraphData sdg = new SensorGraphData();
            sdg.setSensorName("Sensor" + i);

            for (int ar=0;ar<2;ar++) {
                ArrayList<Float> readings = new ArrayList<>();
                readings.add(new Float(10));
                readings.add(new Float(20));
                readings.add(new Float(30));
                sdg.addAttribute("Attr"+ar, readings);
            }
            sdgs.add(sdg);
        }

        String r = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.writeValueAsString(sdgs);
            System.out.println(r);
        }
        catch (Exception exp) {
            fail("Failed to seralize json: " + exp);
        }

    }
}
