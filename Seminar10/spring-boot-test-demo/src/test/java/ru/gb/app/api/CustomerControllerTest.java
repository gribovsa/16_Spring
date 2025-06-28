package ru.gb.app.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.JUnitSpringBootBase;
import ru.gb.app.model.Customer;
import ru.gb.app.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;

import org.slf4j.event.Level;

/**
 * Класс тестирует контроллер нашего приложения
 */
class CustomerControllerTest extends JUnitSpringBootBase {

  // TDD -> TestDrivenDevelopment
  // DataJpaTest
  // Testcontainers -> docker containers
  //

  @Autowired
  WebTestClient webTestClient; // "инжектим" класс, чтобы отправлять запросы на наш сервер
  @Autowired
  CustomerRepository customerRepository;
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Data
  static class JUnitCustomerResponse {
    private Long id;
    private String name;
  }


  @Test
  void testFindByIdSuccess() {
    // подготовил данные
    Customer expected = customerRepository.save(Customer.ofName("random"));

    JUnitCustomerResponse responseBody = webTestClient.get()
      .uri("/api/customer/" + expected.getId())
      .exchange()
      .expectStatus().isOk()
      .expectBody(JUnitCustomerResponse.class)
      .returnResult().getResponseBody();

    Assertions.assertNotNull(responseBody);
    Assertions.assertEquals(expected.getId(), responseBody.getId());
    Assertions.assertEquals(expected.getName(), responseBody.getName());
  }

  @Test
  void testFindByIdNotFound() {
    Long maxId = jdbcTemplate.queryForObject("select max(id) from customer", Long.class);

    webTestClient.get()
      .uri("/api/customer/" + maxId + 1)
      .exchange()
      .expectStatus().isNotFound();
  }


  @Test
  void testGetAll() {

    // Подготовил данные - заполнил тестовый репозиторий
    customerRepository.saveAll(List.of(
      Customer.ofName("first"),
      Customer.ofName("second")
    ));
    List<Customer> expected = customerRepository.findAll();


    // Выполняю http запрос по .uri("/api/customer") и получаю объекты в виде Json
    List<JUnitCustomerResponse> responseBody = webTestClient.get()
      .uri("/api/customer")
      // при инжекции webTestClient доступны нижеописанные методы,
      .exchange()
      //после получения ответа выполняем проверки
      .expectStatus().isOk()
      //ожидаем что тип ответа будет ()
      .expectBody(new ParameterizedTypeReference<List<JUnitCustomerResponse>>() {
      })
      .returnResult()
      .getResponseBody();

    // Далее идёт сравнение фактического и ожидаемого
    Assertions.assertEquals(expected.size(), responseBody.size()); //сначала количество
    for (JUnitCustomerResponse customerResponse : responseBody) {
      boolean found = expected.stream()
        .filter(it -> Objects.equals(it.getId(), customerResponse.getId()))
        .anyMatch(it -> Objects.equals(it.getName(), customerResponse.getName()));
      Assertions.assertTrue(found);
    }
  }

  @Test
  @Disabled
  void testSave() {
    JUnitCustomerResponse request = new JUnitCustomerResponse();

    JUnitCustomerResponse responseBody = webTestClient.post()
      .uri("/api/customer")
      .bodyValue(request)
      .exchange()
      .expectStatus().isCreated()
      .expectBody(JUnitCustomerResponse.class)
      .returnResult().getResponseBody();

    Assertions.assertNotNull(responseBody);
    Assertions.assertNotNull(responseBody.getId());

    Assertions.assertTrue(customerRepository.findById(request.getId()).isPresent());
  }

}

// остановился на 1:22:16