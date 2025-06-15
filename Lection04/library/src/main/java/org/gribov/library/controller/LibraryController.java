package org.gribov.library.controller;

import org.gribov.library.model.Book;
import org.gribov.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController говорит Spring, что этот класс является контроллером, и его
методы должны обрабатывать HTTP-запросы.
@RequestMapping("/books") указывает базовый путь, на который этот
контроллер будет реагировать.
 */
@RestController
@RequestMapping("/books")
public class LibraryController {

    private final LibraryService libraryService;

    /*
        Мы внедряем наш LibraryService через конструктор, чтобы контроллер мог
    использовать его для обработки запросов.
     */
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    /*
        @GetMapping, @PostMapping, @DeleteMapping аннотируют методы, которые
    должны обрабатывать GET, POST и DELETE запросы соответственно.
        @RequestBody и @PathVariable аннотации указывают, что параметр метода
    должен быть извлечен из тела запроса или из части пути запроса.
        Мы возвращаем ResponseEntity, чтобы мы могли указать HTTP-статус ответа и
    любые другие детали, которые нам могут понадобиться.
     */

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(libraryService.getAllBooks(),
                HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(libraryService.addBook(book),
                HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = libraryService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
