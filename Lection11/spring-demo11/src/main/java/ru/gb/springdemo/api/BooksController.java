package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
@Tag(name = "Книги")
public class BooksController {
    //Инжекция зависимости
    @Autowired
    private BookService bookService;


    //ручки
    @GetMapping
    @Operation(summary = "Get all books", description = "Загружает список всех книг, которые есть в библиотеке")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> book = bookService.getAllBook();
        log.info(!book.isEmpty() ? book.toString() : "none");
        return !book.isEmpty()
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get book by id", description = "Загружает книгу с указанным идентификатором")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        Book book = bookService.getBookById(id);
        log.info(book != null ? book.toString() : "none");
        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)// или ResponseEntity.ok(book)
                : ResponseEntity.notFound().build();
    }


    @PostMapping
    @Operation(summary = "Create new book", description = "Создаёт новую книгу в библиотеке")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        log.info(newBook != null ? newBook.toString() : "none");
        return newBook != null
                ? new ResponseEntity<>(newBook, HttpStatus.CREATED)
                : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id", description = "Удаляет книгу с указанным идентификатором из библиотеки")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

}
