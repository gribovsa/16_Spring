package com.example.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Класс BookController для обработки HTTP запросов.
 * ● @RestController: Это аннотация Spring, которая говорит, что данный класс
 * является контроллером и должен быть инициализирован при запуске
 * приложения.
 * ● @RequestMapping(“/books”): Эта аннотация задает базовый маршрут для
 * всех методов в этом контроллере. В данном случае, все наши эндпоинты
 * будут начинаться с “/books”.
 * ● private final BookService bookService; Здесь мы инжектируем наш сервис в
 * контроллер. Это позволяет нам использовать методы сервиса внутри
 * контроллера.
 * ● @Autowired: Эта аннотация Spring автоматически инжектирует бин в
 * конструктор. В данном случае, Spring будет автоматически передавать
 * экземпляр BookService при создании экземпляра BookController.
 * ● @GetMapping; @PostMapping; @PutMapping; @DeleteMapping: Эти
 * аннотации Spring задают HTTP-метод для каждого из методов контроллера.
 * Они соответствуют стандартным CRUD-операциям: получение, создание,
 * обновление и удаление.
 * ● public List findAll(); public ResponseEntity findById(@PathVariable Long id);
 * public Book save(@RequestBody Book book); public Book
 * update(@RequestBody Book book, @PathVariable Long id); public void
 * deleteById(@PathVariable Long id); Это наши методы контроллера. Каждый
 * из этих методов обращается к соответствующему методу сервиса для
 * выполнения операции и возвращает результат.
 * Таким образом, слой контроллеров отвечает за обработку HTTP-запросов и
 * взаимодействует со слоем сервисов для выполнения бизнес-логики.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable Long id) {
        book.setId(id);
        return bookService.save(book);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
