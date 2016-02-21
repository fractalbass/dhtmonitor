package com.phg.dhtmonitor.controller;

import com.phg.dhtmonitor.model.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by milesporter on 2/19/16.
 */
@RestController
public class DHTController {

    @RequestMapping("dht")
    public Dht index() {
        Dht d = new Dht(10,10);
        return d;
    }
}