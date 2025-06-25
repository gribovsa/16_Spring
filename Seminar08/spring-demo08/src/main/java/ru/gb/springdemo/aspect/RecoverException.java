package ru.gb.springdemo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, которую можно ставить только над методами
 * Аннотация работает так: если во время исполнения метода был exception, то не прокидывать его выше
 * и возвращать из метода значение по умолчанию (null, 0, false, ...).
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecoverException {
    Class<? extends RuntimeException>[] noRecoverFor() default {};
}
