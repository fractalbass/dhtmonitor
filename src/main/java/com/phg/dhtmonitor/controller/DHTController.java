package com.phg.dhtmonitor.controller;

import com.phg.dhtmonitor.model.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by milesporter on 2/19/16.
 */
@RestController
public class DHTController {

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value="/temperature/{temperature}/humidity/{humidity}", method=RequestMethod.POST)
    public String dhtPost( @PathVariable String temperature, @PathVariable String humidity) {
        Dht dht = new Dht(Float.parseFloat(temperature), Float.parseFloat(humidity));
        return "OK";
    }

    @RequestMapping(value="/readings/seconds/{seconds}", method=RequestMethod.GET)
    public ArrayList<Dht> dhtSeconds(@PathVariable String seconds) {
        ArrayList dhts = new ArrayList<>();
        Dht dht = new Dht(0,0);
        dhts.add(dht);
        return dhts;
    }

    @RequestMapping(value="/readings/count/{count}", method=RequestMethod.GET)
    public ArrayList<Dht> dhtReadings(@PathVariable String count) {
        ArrayList dhts = new ArrayList<>();
        Dht dht = new Dht(0,0);
        dhts.add(dht);
        return dhts;
    }

}