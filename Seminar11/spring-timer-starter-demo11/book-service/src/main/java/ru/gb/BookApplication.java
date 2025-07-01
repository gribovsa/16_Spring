package ru.gb;

import com.gribov.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Создали модуль book-service, который будет
 * одним из микросервисов
 * Этот микросервис будет клиентом Eureka,
 * в pom пропишем зависимость для Eureka spring-cloud-starter-netflix-eureka-client.
 * И в application.yml укажем адрес Eureka сервера http://localhost:8761/eureka, и имя
 * как будет называться наш микросервис book-service
 */
@Timer
@SpringBootApplication
public class BookApplication {
  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
  }
}