package com.phg.dhtmonitor.model;

import org.springframework.stereotype.Component;

/**
 * Created by milesporter on 2/19/16.
 */

@Component
public class Dht {

    public Dht(float temp, float hmdt) {
        this.timestamp = System.currentTimeMillis();
        this.humidity = hmdt;
        this.temperature = temp;
    }

    public Dht(float temp, float hmdt, long ts) {
        this.timestamp = ts;
        this.humidity = hmdt;
        this.temperature = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    private final float humidity;
    private final float temperature;
    private final long timestamp;

}
