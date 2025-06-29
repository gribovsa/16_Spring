package ru.gb.springdemo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
@AllArgsConstructor
public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  private long readerId;

  /**
   * Идентификатор книги
   */
  private long bookId;

}
