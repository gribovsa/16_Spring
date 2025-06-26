package ru.gb.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.BookProvider;

import java.time.ZoneId;
import java.util.*;
/**
 * Пример контроллера + сервиса + репозитория для нашего микросервиса issue-service
 *  В pom необходимо подключить модуль с моделями
 *   <dependency>
 *               <groupId>ru.gb</groupId>
 *               <artifactId>api</artifactId>
 *               <version>0.0.1-SNAPSHOT</version>
 *   </dependency>
 */
@RestController
@RequestMapping("/api/issue")
public class IssueController {

  private final Faker faker;
  private final BookProvider bookProvider;
  private final List<Issue> issues;

  public IssueController(BookProvider bookProvider) {
    this.faker = new Faker();
    this.bookProvider = bookProvider;
    this.issues = new ArrayList<>();

    refreshData();
  }

  @GetMapping
  public List<Issue> getAll() {
    return issues;
  }

  @GetMapping("/refresh")
  public List<Issue> refresh() {
    refreshData();
    return issues;
  }

  private void refreshData() {
    issues.clear();
    //Заполняю список (репозиторий) выдач
    for (int i = 0; i < 15; i++) {
      Issue issue = new Issue();
      issue.setId(UUID.randomUUID());

      Date between = faker.date().between(startOfYear(), endOfYear()); //выбираю дату из диапазона
      issue.setIssuedAt(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); //конвертирую в формат LocalDate
      issue.setBookId(bookProvider.getRandomBookId()); //здесь записываю книгу с существующим Id, который вернул нам метод bookProvider.getRandomBookId()
      issue.setReaderId(UUID.randomUUID());

      issues.add(issue);
    }
  }

  private Date startOfYear() {
    Calendar instance = Calendar.getInstance();
    instance.set(Calendar.YEAR, 2024);
    instance.set(Calendar.MONTH, Calendar.JANUARY);
    instance.set(Calendar.DAY_OF_MONTH, 1);
    return instance.getTime();
  }

  private Date endOfYear() {
    Calendar instance = Calendar.getInstance();
    instance.set(Calendar.YEAR, 2024);
    instance.set(Calendar.MONTH, Calendar.DECEMBER);
    instance.set(Calendar.DAY_OF_MONTH, 31);
    return instance.getTime();
  }
}
