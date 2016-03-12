package com.phg.dhtmonitor.model;

import java.util.ArrayList;

/**
 * Created by milesporter on 3/10/16.
 * {"temp":[10,20,30]},
 */
public class AttributeReadings {

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    private String attributeName;

    public ArrayList<Float> getAttributeReadings() {
        return attributeReadings;
    }

    public void setAttributeReadings(ArrayList<Float> attributeReadings) {
        this.attributeReadings = attributeReadings;
    }

    private ArrayList<Float> attributeReadings;

}
