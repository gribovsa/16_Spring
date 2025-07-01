package com.gribov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Класс в котором есть тестовый метод, который я буду логировать
 */
@Slf4j
@Component
public class Test {
    @Loggable
    public void testMethod(){
        log.info("Запущен тестовый метод Test.testMethod");
    }
}
