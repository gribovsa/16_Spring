package ru.gb;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

/**
 * Простой класс User
 */
@Data //аннотация Lombok (теперь наш класс по умолчанию имеет getter и setter)
public class User {

  private static long idCounter = 1L;

  private final long id; //setter нет так как переменная финальная
  private String name;   //setter есть

  @JsonCreator
  public User(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public User(String name) {
    this.id = idCounter++;
    this.name = name;
  }

}
