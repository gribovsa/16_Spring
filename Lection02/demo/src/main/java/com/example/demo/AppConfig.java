package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * В Spring Boot вы можете создать классы конфигурации, аннотированные
 * @Configuration, и использовать методы с аннотацией @Bean для определения
 * компонентов, которые будут управляться Spring IoC контейнером.
 * В приведенном примере мы создаем класс AppConfig с аннотацией
 * @Configuration. Затем мы определяем метод myService(), который возвращает
 * новый экземпляр MyServiceImpl. Этот метод аннотирован @Bean, что означает, что
 * Spring создаст и будет управлять экземпляром MyServiceImpl как bean.
 */
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}