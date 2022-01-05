package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
class ApplicationStartup {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    /*@Value("${demo.param}")
    private String demoParam;*/

    @EventListener(ApplicationReadyEvent.class)
    public void appWarmup() {
        log.info("Starting app...");
        //log.info("Value from parameter store: {}", demoParam);
    }

}
