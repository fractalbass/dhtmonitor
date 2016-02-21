package com.phg.dhtmonitor.service;

import com.phg.dhtmonitor.dao.MySqlDao;
import com.phg.dhtmonitor.model.Dht;
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

    public boolean SaveDHT(float temperature, float humidity) {
        boolean success = false;
        try {
            Dht dht = new Dht(temperature, humidity);
            success = mySqlDao.saveDht(dht);
        } catch (Exception exp) {
            success = false;
        }
        return success;
    }

    public ArrayList<Dht> getLastByCount(int count) {
        return mySqlDao.getLastByCount(count);
    }

    public ArrayList<Dht> getLastBySeconds(int seconds) {
        return mySqlDao.getLastBySeconds(seconds);
    }
}
