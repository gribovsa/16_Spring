package ru.gb;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс БД пользователей (обычно является точкой доступа в БД)
 */
//Декларируем БИН userRepository, с помощью аннотации
//в скобках можно задать идентификатор
//по умолчанию идентификатор будет userRepository (имя БИНа с маленькой буквы)

@Component("myUserRepositoryBean")

@Scope("singleton")
//аннотация singleton - единственный БИН,
//prototype - при обращении создаётся экземпляр БИНа

public class UserRepository {

  private final List<User> users;

  public UserRepository() {
    //инициализация списка
    this.users = new ArrayList<>();
    //генерация пользователей
    users.add(new User("Igor"));
    users.add(new User("User #2"));
    users.add(new User("User #3"));
    users.add(new User("User #4"));
    users.add(new User("User #5"));
  }

  public List<User> getAll() {
    //вернёт копию листа, а не исходник
    //при этом исходник нельзя будет испортить
    return List.copyOf(users);
  }

  public User getByName(String name) {
    return users.stream()
      .filter(it -> Objects.equals(it.getName(), name))
      .findFirst()
      .orElse(null);
  }

  public User getById(long id) {
    //находим пользователя в списке с id, если не нашли, вернём null
    return users.stream()
      .filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

}
