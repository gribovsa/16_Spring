package ru.gb;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import ru.gb.api.Book;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
   * (чтобы использовать uri("http://book-service/api/book/random") без указания IP, который мы можем и нее знать
   * и не использовать для этого рукописный метод getBookServiceIp
   */
  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
//    this.eurekaClient = eurekaClient;
  }

  /**
   * Цель метода получить Id книги,
   * обратиться к объекту книга book.getId уже нельзя, так как book - уже в другом приложении (на др сервере, с др Ip и портом)
   * Цель метода вызвать GET http://book-service/api/book/random, получить ID книги и вернуть
   */
  public UUID getRandomBookId() {
    Book randomBook = webClient.get()
      .uri("http://book-service/api/book/random")
      .retrieve()
      .bodyToMono(Book.class) //поддержка реактивного API
      .block();

    return randomBook.getId();
  }

  // round robbin

//  /**
//   * Метод, который позволяет получить IP микросервиса
//   */
//  private String getBookServiceIp() {
//    Application application = eurekaClient.getApplication("BOOK-SERVICE");
//    //под одним именем BOOK-SERVICE может быть зарегистрировано несколько микросервисов (например реплик, копий), поэтому список
//    List<InstanceInfo> instances = application.getInstances();
//
//    int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
//    InstanceInfo randomInstance = instances.get(randomIndex);
//    return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
//  }

}
