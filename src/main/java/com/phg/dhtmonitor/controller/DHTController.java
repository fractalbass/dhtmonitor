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

    @RequestMapping(value="/temperature/{temperature}/humidity/{humidity}", method=RequestMethod.POST)
    public ResponseEntity<?> dhtPost(@PathVariable String temperature, @PathVariable String humidity) {
        dhtService.SaveDHT(Float.parseFloat(temperature), Float.parseFloat(humidity));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value="/readings/seconds/{seconds}", method=RequestMethod.GET)
    public ArrayList<Dht> dhtSeconds(@PathVariable String seconds) {
        ArrayList<Dht> dhts = dhtService.getLastBySeconds(Integer.parseInt(seconds));
        return dhts;
    }

    @RequestMapping(value="/readings/count/{count}", method=RequestMethod.GET)
    public ArrayList<Dht> dhtReadings(@PathVariable String count) {
        ArrayList<Dht> dhts = dhtService.getLastByCount(Integer.parseInt(count));
        return dhts;
    }

}