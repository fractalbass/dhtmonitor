package com.phg.dhtmonitor.model;

/**
 * Created by milesporter on 2/25/16.
 */
public class DhtMeasurement {

    private float temperature;

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    private float humidity;

}
