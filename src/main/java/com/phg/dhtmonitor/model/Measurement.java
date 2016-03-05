package com.phg.dhtmonitor.model;

/**
 * Created by milesporter on 3/5/16.
 */
public class Measurement {

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String sensor;
    public String attribute;
    public float val;
    public long timestamp;

    public Measurement(String sensor, String attribute, float val) {
        this.timestamp = System.currentTimeMillis();
        this.attribute = attribute;
        this.val = val;
        this.sensor = sensor;
    }

    public Measurement(String sensor, String attribute, float val, long ts) {
        this.timestamp = ts;
        this.attribute = attribute;
        this.val = val;
        this.sensor = sensor;
    }

}
