package com.phg.dhtmonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by milesporter on 2/19/16.
 */
@RestController
public class DHTController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}