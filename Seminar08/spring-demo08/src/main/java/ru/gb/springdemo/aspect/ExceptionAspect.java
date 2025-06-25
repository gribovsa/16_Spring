package ru.gb.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component

/**
 * Класс в котором описывается логика работы советов - Advice
 */
public class ExceptionAspect {


    /**
     * Бин pointcut - точка среза - то куда мы встраиваем наш бин совет Advice
     * в нашем случае это методы и классы с аннотацией @RecoverException
     */
    @Pointcut("@annotation(ru.gb.springdemo.aspect.RecoverException)")
    public void exceptionRec() {
    }



    /**
     * Бин Advice - совет типа Around, который работает вместо вызова.
     */
    @Around("exceptionRec()")
    public Object exceptionAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RecoverException annotation =
                ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(RecoverException.class);
        List<Class<? extends RuntimeException>> exceptions = List.of(annotation.noRecoverFor());
        try {
            return proceedingJoinPoint.proceed(); //здесь запускается метод над которым будет стоять аннотация @RecoverException
        } catch (Throwable e) {
            //метод с аннотацией  выбрасывает исключение, которое мы обрабатываем
            log.info("Метод {} вызвал исключение {} - {}", proceedingJoinPoint.getSignature(), e.getClass(), e.getMessage());
            for (Class<? extends RuntimeException> exceptionClass : exceptions) {
                if (exceptionClass.isAssignableFrom(e.getClass())) {
                    log.info("Throws exception {}", e.getClass());
                    throw e;
                }
            }
        }
        return null;

    }

}