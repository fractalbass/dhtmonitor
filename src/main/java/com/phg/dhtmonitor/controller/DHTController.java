package com.phg.dhtmonitor.controller;

import com.phg.dhtmonitor.model.*;
import com.phg.dhtmonitor.service.DhtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by milesporter on 2/19/16.
 */
@RestController
public class DHTController {

    @Autowired
    DhtService dhtService;

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value="/temperature/{temperature}/humidity/{humidity:.+}/sensor/{sensor}", method=RequestMethod.POST)
    public ResponseEntity<?> dhtPost(@PathVariable String temperature, @PathVariable String humidity, @PathVariable String sensor) {
        dhtService.SaveDHT(sensor, Float.parseFloat(temperature), Float.parseFloat(humidity));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/sensor/{sensor}/attribute/{attribute}/value/{value:.+}", method=RequestMethod.POST)
    public ResponseEntity<?> dhtMeasurementPost(@PathVariable String value, @PathVariable String attribute, @PathVariable String sensor) {
        dhtService.SaveValue(sensor, attribute, Float.parseFloat(value));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value="/readings/seconds/{seconds}", method=RequestMethod.GET)
    public ArrayList<Measurement> dhtSeconds(@PathVariable String seconds) {
        ArrayList<Measurement> dhts = dhtService.getLastBySeconds(Integer.parseInt(seconds));
        return dhts;
    }

    @RequestMapping(value="/readings/count/{count}", method=RequestMethod.GET)
    public ArrayList<Measurement> dhtReadings(@PathVariable int count) {
        ArrayList<Measurement> dhts = dhtService.getLastByCount(count);
        return dhts;
    }

    @RequestMapping(value="/readings/sensor/{sensor}/seconds/{seconds}", method=RequestMethod.GET)
    public ArrayList<Measurement> dhtSecondSensor(@PathVariable String seconds, @PathVariable String sensor) {
        ArrayList<Measurement> dhts = dhtService.getLastBySecondsAndSensor(sensor, Integer.parseInt(seconds));
        return dhts;
    }

    @RequestMapping(value="/readings/sensor/{sensor}/count/{count}", method=RequestMethod.GET)
    public ArrayList<Measurement> dhtReadingSensor(@PathVariable int count, @PathVariable String sensor) {
        ArrayList<Measurement> dhts = dhtService.getLastByCountAndSensor(sensor, count);
        return dhts;
    }

}