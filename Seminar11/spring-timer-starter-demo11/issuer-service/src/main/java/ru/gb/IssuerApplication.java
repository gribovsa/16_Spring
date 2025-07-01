package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Это будет второй микросервис, отвечающий за выдачу книг
 * issue-service
 * Этот микросервис будет клиентом Eureka, настраиваем его также как book-service
 */
@SpringBootApplication
public class IssuerApplication {
  public static void main(String[] args) {
    SpringApplication.run(IssuerApplication.class, args);
  }
}