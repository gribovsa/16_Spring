package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Пример внедрения зависимости
 * через конструктор
 * через сеттер
 * через аннотацию Autowired
 */
@Service
public class Car {

    @Autowired
    Engine engine;

    public void start(){
        engine.go();
    }
 }
