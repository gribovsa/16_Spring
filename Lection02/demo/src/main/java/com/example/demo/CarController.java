package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Здесь @RestController — это аннотация Spring, которая говорит, что этот класс
 * является контроллером, и он должен обрабатывать веб-запросы.
 * @GetMapping("/") - это другая аннотация Spring, которая указывает, что метод hello() должен
 * обрабатывать HTTP-запросы GET на URL “http://localhost:8080/car”.
 */

@RestController
public class CarController {

    @Autowired
    Car car;

    /**
     * Перейдя по ссылке http://localhost:8080/car будет запущен метод car.start();
     */
    @GetMapping("/car")
    public String startCar() {
        car.start();
        return "Автомобиль запущен";
    }
}
