package com.gribov;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Класс в котором описывается логика работы советов Advice
 */
@Slf4j
@Aspect
@Component
public class TimerAspect {
    /**
     * Бин pointcut - точка среза - то куда мы встраиваем наш бин совет Advice
     * в нашем случае это методы с аннотацией @Timer
     */
    @Pointcut("@annotation(com.gribov.Timer")
    public void timerExecMethod(){}


    /**
     * Бин pointcut - точка среза - то куда мы встраиваем наш бин совет Advice
     * в нашем случае это классы с аннотацией @Timer
     */
    @Pointcut("within(@com.gribov.Timer *)")
    public void timerExecClass(){}



    /**
     * Бин Advice - совет типа Around, который работает вместо вызова.
     * В аргументе аннотации указывается аргумент pointcut, у нас это имена методов timerExecClass() timerExecMethod()
     * В аргументе метода указывается JoinPoint - точка присоединения данного совета Advice.
     */
    @Around("timerExecClass() || timerExecMethod()")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis() - begin;
        log.info("CLASS or METHOD {} EXECUTING {}", joinPoint.getSignature(),end);
        return result;
    }

}
