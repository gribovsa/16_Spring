package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Факт выдачи книг")
public class IssuerController {

  @Autowired
  private IssuerService issuerServices;


  /**
   * Post метод создания запроса на выдачу книги.
   * @param request получаем объект IssueRequest
   * @return возвращает объект Issue
   */
  @PostMapping
  @Operation(summary = "Create new issue", description = "Создаёт новый факт выдачи книги читателю")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    Issue issue;
    try {
      issue = issuerServices.createIssue(request);
      log.info("201 -запрос выполнен успешно и привёл к созданию ресурса {}", issue.toString());
      return new ResponseEntity<>(issue,HttpStatus.CREATED);
      //подсмотрел
    } catch (NoSuchElementException e) {
      log.info("404 -  сервер не может найти данные согласно запросу");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (RuntimeException e) {
      log.info("409 -  запрос не может быть выполнен из-за конфликтного обращения к ресурсу");
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

  }

  /**
   * Get метод получения списка всех выдач книг Issue
   */
  @GetMapping
  public ResponseEntity<List<Issue>> getAllIssue() {
    List<Issue> issues = issuerServices.getAllIssue();
    log.info(!issues.isEmpty() ? issues.toString() : "Список записей о выдаче книг пуст");
    return !issues.isEmpty()
            ? new ResponseEntity<>(issues, HttpStatus.OK)
            : ResponseEntity.notFound().build();
  }


  /**
   * Get метод получения факта выдачи книги (объекта Issue) по id
   * (получить описание факта выдачи книги)
   * @param id получаем идентификатор
   * @return возвращаем найденный объект issue
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get issue by id", description = "Загружает факт выдачи с указанным идентификатором факта")
  //---------------------------
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Искомый факт выдачи",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = Issue.class))}),
          @ApiResponse(responseCode = "404", description = "Факт выдачи не найден",
                  content = @Content)})
  //---------------------------
  public ResponseEntity<Issue> getInfoIssueById(@PathVariable Long id) {
    final Issue issue;
    issue = issuerServices.getInfoById(id);
    return issue != null
            ? ResponseEntity.ok(issue)
            : ResponseEntity.notFound().build();
  }

  /**
   * Put метод изменения факта выдачи книги - метод закрытия факта выдачи, отметкой о возврате
   * @param id получаем идентификатор
   * @return возвращаем закрытый объект Issue
   */
  @PutMapping("/{id}")
  @Operation(summary = "Update issue by id, add return time", description = "Вносит изменение в факт выдачи - ставит отметку о возврате в виде времени возврата")
  //--------------------------
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Факт выдачи изменён отметкой о возврате",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = Issue.class))}),
          @ApiResponse(responseCode = "409", description = "Неверный ID факта выдачи, факт выдачи не найден",
                  content = @Content)})
  //--------------------------
  public ResponseEntity<Issue> returnedIssue(@PathVariable Long id) {
    Issue reurnedIssue = issuerServices.setReturnedIssue(id);
    return reurnedIssue != null
            ? ResponseEntity.ok(reurnedIssue)
            : ResponseEntity.badRequest().build();
  }

}
