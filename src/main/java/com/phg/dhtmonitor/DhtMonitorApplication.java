package com.phg.dhtmonitor;

import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"com.phg.dhtmonitor.controller",
                "com.phg.dhtmonitor.service",
                "com.phg.dhtmonitor.dao"})
@SpringBootApplication
public class DhtMonitorApplication {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(DhtMonitorApplication.class);
        sa.addListeners(new ApplicationPidFileWriter("dhtmonitor.pid"));

        ApplicationContext ctx = sa.run(args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}