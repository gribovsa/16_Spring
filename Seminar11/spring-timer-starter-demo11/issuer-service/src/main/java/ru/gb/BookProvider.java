package ru.gb;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import ru.gb.api.Book;

/**
 * Класс - тестовое взаимодействие между микросервисами
 * book-service и issue-service используя http вызов.
 */
@Service
public class BookProvider {

  // Классы в Java, которые позволяют осуществить http  вызов
  // HttpClient - java.net
  // RestTemplate - spring.web
  // WebClient - spring.reactive (мы его используем, добавим в pom зависимость spring-boot-starter-webflux)

  private final WebClient webClient;
//  private final EurekaClient eurekaClient;

  /**
   * В конструкторе вызвали WebClient, и конфигурируем http вызов
   * Балансировщик loadBalancerExchangeFilterFunction позволяет получить IP микросервиса
   * (чтобы использовать uri("<a href="http://book-service/api/book/random">...</a>") без указания IP, который мы можем и нее знать
   */
  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
  }


//  /**
//   * Цель метода получить Id книги используя запрос http
//   */
//  public UUID getRandomBookId() {
//    Book randomBook = webClient.get()
//            .uri("http://book-service/api/book/random")
//            .retrieve()
//            .bodyToMono(Book.class) //поддержка реактивного API
//            .block();
//    return randomBook.getId();
//  }

  /**
   * Цель метода получить книгу у другого микросервиса используя запрос http
   */
  public Book getRandomBook() {
    Book randomBook = webClient.get()
            .uri("http://book-service/api/book/random")
            .retrieve()
            .bodyToMono(Book.class) //поддержка реактивного API
            .block();
    return randomBook;
  }

}
