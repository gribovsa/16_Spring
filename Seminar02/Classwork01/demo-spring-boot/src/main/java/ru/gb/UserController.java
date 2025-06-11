package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Controller

/**
 * Класс, который принимает внешние http запросы
 */
// @Controller //Аннотация означает что класс является БИН и является контроллером
@RestController
// @RestController все аннотации помечаются @ResponseBody, тюе возвращается не страница, а объекты
@RequestMapping("/users")
public class UserController {

  // REST
  // GET /users/{id} => 404 (Not Found)
  // GET /users?nameLike='Igor%' => 204 (No Content)

  // DELETE /users/{id} - удаляет
  // POST   /users/      {"id": "1", "name": "newName"} - создает
  // PUT    /users/{id}  {"id": "1", "name": "newName"} - изменение

  // HTTP HyperText Transfer Protocol
  // GET POST PUT PATCH DELETE ... HEAD OPTIONS

  // http://ip-address/users/all -> List<User>
  // http://ip-address/users/1 -> User(1, Igor)
  // http://ip-address/users?name=Igor -> User(1, Igor)


  private final UserRepository repository;

  // Пример инжекции зависимости (Injection dependency) через конструктор
  // самый правильный способ
  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }


  // построение html страницы, отображение текста
  @GetMapping(value = "/test", produces = MediaType.TEXT_HTML_VALUE)
  public String test() {
    return """
      <h1>Title</h1>
      """;
  }


  // http://ip-address/users/all -> List<User>
  @GetMapping("/all")
  public List<User> getUsers() {
    return repository.getAll();
  }

  // http://ip-address/users/1 -> User(1, Igor)
  // извлечение с использованием переменной, конструкция {id}
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  public User getUser(@PathVariable long id) {
    return repository.getById(id);
  }

  // http://ip-address/users?name=Igor -> User(1, Igor)
  @GetMapping
  public User getUserByName(@RequestParam String name) {
    return repository.getByName(name);
  }

  // запрос, который обновит пользователя
  @PatchMapping("/{id}")
  public User updateUser(@PathVariable long id, @RequestBody User user) {
    User existsUser = repository.getById(id);
    if (existsUser == null) { // ответ 404
      throw new IllegalArgumentException();
    }

    existsUser.setName(user.getName());
    return existsUser;
  }

}
