package ru.gb.springdemo.aspect;

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
     * в нашем случае это методы и классы с аннотацией @Timer
     */
    @Pointcut("@annotation(ru.gb.springdemo.aspect.Timer")
    public void timerExecMethod(){}

    @Pointcut("within(@ru.gb.springdemo.aspect.Timer *)")
    public void timerExecClass(){}


    /**
     * Бин Advice - совет типа Around, который работает вместо вызова.
     * в аргументе аннотации указывается аргумент pointcut, у нас это имена методов (или для классов или для методов)
     * В аргументе метода указывается JoinPoint - точка присоединения данного совета Advice.
     * Здесь нам доступны некоторые действия, которые мы можем совершить применительно к методу (к которому мы присоединились).
     */
    @Around("timerExecClass() || timerExecMethod()")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed(); //здесь запускается метод над которым будет стоять аннотация @Timer
        long end = System.currentTimeMillis() - begin;
        log.info("Метод {} выполняется {}", joinPoint.getSignature(),end);
        return result;
    }

}