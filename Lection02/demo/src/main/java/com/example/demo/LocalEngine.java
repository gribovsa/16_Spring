package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary //по умолчанию запускать этот профиль
@Profile("local")
public class LocalEngine implements Engine{
    public LocalEngine() {
        System.out.println("Двигатель запущен на моём ноутбуке");
    }

    public void go(){
        System.out.println("Поехали медленно!");
    }
}
