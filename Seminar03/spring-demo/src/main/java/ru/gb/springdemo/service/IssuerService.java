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
import java.util.List;
import java.util.NoSuchElementException;


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
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    if (issueRepository.getListReturnedIssue().size() >= maxAllowedBooks) {
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
   * Метод ищет факт выдачи в репозитории по id
   * @param id получает идентификатор
   * @return возвращает факт выдачи книги Issue
   */
  public Issue getInfoById(Long id) {
    return issueRepository.getIssueById(id);
  }

  /**
   * Метод ищет факт выдачи в репозитории по id читателя
   * @param id получает идентификатор читателя
   * @return возвращает список фактов выдачи книги и время
   */
  public List<Issue> getIssuesByIdReader(Long id) {
    return issueRepository.getIssuesByIdReader(id);
  }

  /**
   * Метод закрывает факт выдачи книги
   * @param id получает идентификатор факта
   * @return возвращает закрытый факт
   */
  public Issue setReturnedIssue(Long id) {
    return issueRepository.setReturnedIssue(id);
  }

  /**
   * Метод возвращает список всех фактов выдачи книг пользователям
   * @return список фактов
   */
  public List<Issue> getAllIssue() {
    return issueRepository.getAllIssues();
  }

}
