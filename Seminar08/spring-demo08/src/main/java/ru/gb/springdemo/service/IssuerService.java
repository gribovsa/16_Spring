package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class IssuerService {

  // Spring это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  //Читаем максимально возможное количество книг у читателя на руках
  //из файла Application.yml, если там запись отсутствует, то по умолчанию будет 1шт
  @Value("${application.max-allowed-books:1}")
  private int maxAllowedBooks;


  /**
   * Метод регистрации факта выдачи книги
   * @param request получаем объект IssueRequest - запрос
   * @return возвращаем объект Issue, прежде добавив в репозиторий выдач
   */
  public Issue createIssue(IssueRequest request) {
    if (bookRepository.findById(request.getBookId()).isEmpty()) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.findById(request.getReaderId()).isEmpty()) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    // вызываю метод, который вернёт список фактов выдачи по id пользователя и времени возврата null (т.е книга у него на руках)
    if (issueRepository.findByReaderIdAndReturnedTimestamp(request.getReaderId(), null).size() >= maxAllowedBooks) {
      log.error("Отказано в выдаче читателю ID - \"{}\" из-за превышения лимита", request.getReaderId());
      throw new RuntimeException("Отказано в выдаче читателю ID - " + request.getReaderId() + " из-за превышения лимита");
    }
    log.info("Запрос разрешен {}", request);
    //Если всё ок, то создаём экземпляр класса Issue
    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    log.info(issue.toString());
    issueRepository.save(issue);
    return issue;
  }

  /**
   * Метод возвращает список всех фактов выдачи книг пользователям
   * @return список фактов
   */
  public List<Issue> getAllIssue() {
    return issueRepository.findAll();
  }

  /**
   * Метод ищет факт выдачи в репозитории по id
   * @param id получает идентификатор
   * @return возвращает факт выдачи книги Issue
   */
  public Issue getInfoById(Long id) {
    return issueRepository.findById(id).orElse(null);
  }

  /**
   * Метод ищет факт выдачи в репозитории по id читателя
   * @param id получает идентификатор читателя
   * @return возвращает список фактов выдачи книги и время
   */
  public List<Issue> getIssuesByIdReader(Long id) {
    return issueRepository.findByReaderId(id);
  }

  /**
   * Метод закрывает факт выдачи книги
   * @param id получает идентификатор факта
   * @return возвращает закрытый факт
   */
  public Issue setReturnedIssue(Long id) {
    Optional<Issue> returnedIssue = issueRepository.findById(id);
    returnedIssue.get().setReturnedTimestamp(LocalDateTime.now());
    return issueRepository.save(returnedIssue.get());
  }

}
