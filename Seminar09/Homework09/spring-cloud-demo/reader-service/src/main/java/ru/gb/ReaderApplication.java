package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Создали модуль reader-service, который будет
 * одним из микросервисов
 * Этот микросервис будет клиентом Eureka,
 * в pom пропишем зависимость для Eureka spring-cloud-starter-netflix-eureka-client.
 * И в application.yml укажем адрес Eureka сервера http://localhost:8761/eureka, и имя
 * как будет называться наш микросервис reader-service
 */
@SpringBootApplication
public class ReaderApplication {
  public static void main(String[] args) {
    SpringApplication.run(ReaderApplication.class, args);
  }
}