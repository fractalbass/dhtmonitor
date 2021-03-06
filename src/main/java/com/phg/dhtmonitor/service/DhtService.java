package com.phg.dhtmonitor.service;

import com.phg.dhtmonitor.dao.MySqlDao;
import com.phg.dhtmonitor.model.Dht;
import com.phg.dhtmonitor.model.Measurement;
import com.phg.dhtmonitor.model.SensorGraphData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by milesporter on 2/21/16.
 */
@Component
public class DhtService {

    @Autowired
    private MySqlDao mySqlDao;

    public void setDao(MySqlDao dao) {
        mySqlDao = dao;
    }

    public boolean SaveDHT(String sensor, float temperature, float humidity) {
        boolean success = false;
        try {
            Measurement mt  = new Measurement(sensor, "temperature", temperature);
            success = mySqlDao.saveMeasurement(mt);
            if (success) {
                Measurement mh = new Measurement(sensor, "humidity", humidity);
                success = mySqlDao.saveMeasurement(mh);
            }
        } catch (Exception exp) {
            success = false;
        }
        return success;
    }

    public boolean SaveValue(String sensor, String attribute, float value) {
        boolean success = false;
        try {
            Measurement m  = new Measurement(sensor, attribute, value);
            success = mySqlDao.saveMeasurement(m);
        } catch (Exception exp) {
            success = false;
        }
        return success;
    }

    public ArrayList<String> getSensors() {
        return mySqlDao.getSensors();
    }

    public ArrayList<String> getAttributesBySensor(String sensor) {
        return mySqlDao.getAttributesBySensors(sensor);
    }

    public ArrayList<Measurement> getLastByCount(int count) {
        return mySqlDao.getLastByCount(count);
    }

    public ArrayList<Measurement> getLastBySeconds(int seconds) {
        return mySqlDao.getLastBySeconds(seconds);
    }

    public ArrayList<Measurement> getLastByCountAndSensor(String sensor, int count) {
        return mySqlDao.getLastByCountAndServer(sensor, count);
    }

    public ArrayList<Measurement> getLastBySecondsAndSensor(String sensor, int seconds) {
        return mySqlDao.getLastBySecondsandServer(sensor, seconds);
    }

    public ArrayList<SensorGraphData> getSensorGraphData(int count) {
        ArrayList<SensorGraphData> sdgs = new ArrayList<>();


        ArrayList<String> sensors = mySqlDao.getSensors();

        for (String sensor : sensors) {
            SensorGraphData sdg = new SensorGraphData();
            sdg.setSensorName(sensor);
            ArrayList<String> attributes = mySqlDao.getAttributesBySensors(sensor);
            for (String attribute : attributes) {
                ArrayList<Measurement> measurements = mySqlDao.getLastMeasurementBySensorAndAttribute(sensor,attribute, count);
                sdg.addAttribute(attribute, measurements);
            }
            sdgs.add(sdg);
        }
        return sdgs;
    }
}
