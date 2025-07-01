package com.gribov;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.METHOD}) //для классов и методов
@Retention(RetentionPolicy.RUNTIME) //применима всегда
public @interface Loggable {
}

