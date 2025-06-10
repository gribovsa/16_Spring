package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ProductionMyService implements MyService{
    // реализация для производства
    @Override
    public String getMessage() {
        return "Production Service";
    }
}

