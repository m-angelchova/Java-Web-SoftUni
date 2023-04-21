package com.softuni.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(TestScheduler.class);

    @Scheduled(fixedRate = 500)
    public void doInBackGround(){
        LOGGER.info("In my scheduler!");
    }
}
