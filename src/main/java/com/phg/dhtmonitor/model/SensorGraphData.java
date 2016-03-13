package com.phg.dhtmonitor.model;

import java.util.ArrayList;

/**
 *            {"sensor1":[
 //                {"temp":[10,20,30]},
 //                {"humidity":[40,50,60]}
 //            ]
 //            },
 //            {"sensor2":[
 //                {"temp":[70,80,90]},
 //                {"humidity":[100,110,120]}
 //            ]
 //            },
 //            {"sensor3": [
 //                {"light":[1,2,3,4,5,6,7,8]}
 //            ]
 //            }
 */

public class SensorGraphData {

    private String sensorName;

    private ArrayList<AttributeReadings> attributeReadings;

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public ArrayList<AttributeReadings> getReadings() {
        return attributeReadings;
    }

    public void setMeasurements(ArrayList<AttributeReadings> readings) {
        this.attributeReadings = readings;
    }



    public void addAttribute(String name, ArrayList<Measurement> measurements) {
        if (attributeReadings==null) {
            attributeReadings = new ArrayList<>();
        }
        AttributeReadings ar = new AttributeReadings();
        ar.setAttributeName(name);
        ar.setAttributeMeasurements(measurements);
        attributeReadings.add(ar);
    }
}
