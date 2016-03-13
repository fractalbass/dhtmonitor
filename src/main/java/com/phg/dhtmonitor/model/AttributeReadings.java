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

    public ArrayList<Measurement> getAttributeReadings() {
        return attributeMeasurements;
    }

    public void setAttributeMeasurements(ArrayList<Measurement> attributeMeasurements) {
        this.attributeMeasurements = attributeMeasurements;
    }

    private ArrayList<Measurement> attributeMeasurements;

}
