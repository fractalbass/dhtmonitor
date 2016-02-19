package com.phg.dhtmonitor.model;

/**
 * Created by milesporter on 2/19/16.
 */
public class Dht {

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private float humidity;
    private float temperature;
    private long timestamp;

}
