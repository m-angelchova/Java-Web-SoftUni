package com.softuni.aop.sample1;

import com.softuni.aop.SampleComponent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Sample1Aspect {

    private final Logger LOGGER = LoggerFactory.getLogger(SampleComponent.class);


    @Pointcut("execution(* com.softuni.aop.SampleComponent.*(..))")
    void allSampleComponentMethods(){}

/*    @Before("allSampleComponentMethods()")
    public void beforeEachMethod(JoinPoint joinPoint){
        LOGGER.info("Before a method {} ", joinPoint.getSignature());
    }*/


    @Pointcut("execution(* com.softuni.aop.SampleComponent.echoSmth(..))")
    void echoMethod(){}

    @After("echoMethod()")
    void echoAdvice(){
        LOGGER.info("After echo!");
    }

/*    @AfterThrowing(pointcut = "allSampleComponentMethods()", throwing = "ex")
    void afterThrowing(JoinPoint joinPoint, Exception ex){
        LOGGER.info("After the method {} there is an exception {}!",
                joinPoint.getSignature(), ex.getClass().getSimpleName());
    }*/


    @Pointcut("execution(* com.softuni.aop.SampleComponent.concatTwoStrings(..))")
    void forConcat(){}

    @Around("forConcat() && args(s1, s2)")
    Object aroundConcat(ProceedingJoinPoint pcj, String s1, String s2) throws Throwable {
        LOGGER.info("Concat method was called with args: {} and {}", s1, s2);

        //before the method is called ^^^

        String m1 = "~" + s1 + "~";
        String m2 = "~" + s2 + "~";

        Object result = pcj.proceed(new Object[]{m1, m2});

        //after is called vvv

        return "muchos gracis senior: " + result;
    }
}
