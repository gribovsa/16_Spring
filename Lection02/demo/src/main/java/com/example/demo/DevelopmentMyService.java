package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * В этом примере, в зависимости от активного профиля, будет выбрана
 * соответствующая реализация сервиса. Это дает большую гибкость при
 * конфигурации вашего приложения для разных сред.
 */
@Service
@Profile("development")
public class DevelopmentMyService implements MyService {
    // реализация для разработки
    @Override
    public String getMessage() {
        return "Development Service";
    }

}
