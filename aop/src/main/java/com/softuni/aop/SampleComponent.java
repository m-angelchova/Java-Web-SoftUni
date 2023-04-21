package com.softuni.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleComponent {
    private final Logger LOGGER = LoggerFactory.getLogger(SampleComponent.class);

    public void sayHello(){
        LOGGER.info("Hello, world!");
    }

    public void doSmthWrong(){
        throw new NullPointerException("Oops! Exception!");
    }

    public void echoSmth(String something){
        LOGGER.info("What I want to echo is {}", something);
    }

    public String concatTwoStrings(String one, String two){
        return one + two;
    }
}
