package ru.gb.springdemo.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс - читатель
 */
@Slf4j
@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private Long id;
  private String name;

  public Reader(String name) {
    this.id = sequence++;
    this.name = name;
  }
}
