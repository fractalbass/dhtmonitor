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

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    private String sensorName;

    public ArrayList<AttributeReadings> getAttributeReadings() {
        return attributeReadings;
    }

    public void setAttributeReadings(ArrayList<AttributeReadings> attributeReadings) {
        this.attributeReadings = attributeReadings;
    }

    private ArrayList<AttributeReadings> attributeReadings;

    public void addAttribute(String name, ArrayList<Float> readings) {
        if (attributeReadings==null) {
            attributeReadings = new ArrayList<>();
        }
        AttributeReadings ar = new AttributeReadings();
        ar.setAttributeName(name);
        ar.setAttributeReadings(readings);
        attributeReadings.add(ar);
    }
}
