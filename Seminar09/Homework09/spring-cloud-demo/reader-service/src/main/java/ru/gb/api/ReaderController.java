package ru.gb.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Пример контроллера +сервиса + репозитория для нашего микросервиса reader-service
 * В pom необходимо подключить модуль с моделями
 * <dependency>
 *             <groupId>ru.gb</groupId>
 *             <artifactId>api</artifactId>
 *             <version>0.0.1-SNAPSHOT</version>
 * </dependency>
 */
@RestController
@RequestMapping("/api/reader")
public class ReaderController {

  private final Faker faker;
  private final List<Reader> readers; //интересная библиотека в java, которая генерирует данные

  public ReaderController() {
    this.faker = new Faker();
    final List<Reader> readers = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      Reader reader = new Reader();
      reader.setId(UUID.randomUUID());
      reader.setFirstName(faker.name().firstName());
      reader.setLastName(faker.name().lastName());
      readers.add(reader);
    }

    this.readers = List.copyOf(readers);
  }

  @GetMapping
  public List<Reader> getAll() {
    return readers;
  }

  @GetMapping("/random")
  public Reader getRandom() {
    final int randomIndex = faker.number().numberBetween(0, readers.size());
    return readers.get(randomIndex);
  }

}
