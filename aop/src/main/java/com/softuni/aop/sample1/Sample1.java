package com.softuni.aop.sample1;

import com.softuni.aop.SampleComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sample1 implements CommandLineRunner {

    private final SampleComponent sampleComponent;
    private final Logger LOGGER = LoggerFactory.getLogger(Sample1.class);

    public Sample1(SampleComponent sampleComponent) {
        this.sampleComponent = sampleComponent;
    }

    @Override
    public void run(String... args) throws Exception {
//        sampleComponent.sayHello();
//        sampleComponent.echoSmth("something");
//        try {
//            sampleComponent.doSmthWrong();
//        } catch (Exception e){
//            LOGGER.info("Oh no : {}", e.getMessage());
//        }
        LOGGER.info("Por ejemplo: {}", sampleComponent.concatTwoStrings("uno y ", "dos"));

    }
}
