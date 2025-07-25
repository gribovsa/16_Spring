package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Микросервис Discovery (его реализация Eureka)
 * Это (Eureka server) сервис  регистрации всех микросервисов
 * позволит найти микросервисы, не зная их IP, портов итд.
 * В pom  прописываем зависимость spring-cloud-starter-netflix-eureka-server
 * port: 8761 - стандартный порт
 * localhost:8761/eureka/apps - можно увидеть все зарегистрированные микросервисы
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryService {
  public static void main(String[] args) {
    SpringApplication.run(DiscoveryService.class, args);
  }
}