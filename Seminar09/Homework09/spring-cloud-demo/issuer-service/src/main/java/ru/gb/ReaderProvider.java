package ru.gb;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.api.Book;
import ru.gb.api.Reader;

import java.util.UUID;

/**
 * Класс - тестовое взаимодействие между микросервисами
 * reader-service и issue-service используя http вызов.
 */
@Service
public class ReaderProvider {

  private final WebClient webClient;

  /**
   * В конструкторе вызвали WebClient, и конфигурируем http вызов
   * Балансировщик loadBalancerExchangeFilterFunction позволяет получить IP микросервиса
   * (чтобы использовать uri("<a href="http://reader-service/api/reader/random">...</a>") без указания IP, который мы можем и нее знать
   */
  public ReaderProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
  }


//  /**
//   * Цель метода получить Id читателя
//   */
//  public UUID getRandomReaderId(){
//    Reader randomReader = webClient.get()
//            .uri("http://reader-service/api/reader/random")
//            .retrieve()
//            .bodyToMono(Reader.class) //поддержка реактивного API
//            .block();
//    return randomReader.getId();
//  }

  /**
   * Цель метода получить читателя у другого микросервиса используя запрос http
   */
  public Reader getRandomReader(){
    Reader randomReader = webClient.get()
            .uri("http://reader-service/api/reader/random")
            .retrieve()
            .bodyToMono(Reader.class) //поддержка реактивного API
            .block();
    return randomReader;
  }

}
