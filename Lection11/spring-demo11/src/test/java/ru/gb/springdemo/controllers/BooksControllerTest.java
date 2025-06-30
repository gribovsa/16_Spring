package ru.gb.springdemo.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.service.BookService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Класс тестирует контроллер нашего приложения
 */
@Slf4j
class BooksControllerTest extends JUnitSpringBootBase{
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    // Инжекция класса spring framework, чтобы отправлять запросы на контроллер нашего сервера.
    // Еще требуется в pom добавить зависимость spring-boot-starter-webflux.
    @Autowired
    WebTestClient webTestClient;


    /**
     * Тест ручки GET
     */
    @Test
    void getAllBook() {
        //Заполняю репозиторий
        bookRepository.saveAll(List.of(new Book("Test1"), new Book("Test2")));
        List<Book> books = bookService.getAllBook();

        // Выполняю http запрос по .uri("/api/book") и получаю объекты в виде Json
        List<Book> booksWeb = webTestClient.get()
                .uri("/book")
                // при инжекции webTestClient доступны нижеописанные методы,
                .exchange()
                // после получения ответа выполняем проверку статуса
                .expectStatus().isOk()
                // ожидаем что тип ответа будет ()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {})
                .returnResult()
                .getResponseBody();
        // Далее идёт сравнение фактического и ожидаемого
        // Сравнение количества
        Assertions.assertEquals(booksWeb.size(),books.size());
        // Сравнение содержимого - id и name
        for (Book book : booksWeb) {
            boolean found = books.stream()
                    .filter(it -> Objects.equals(it.getId(), book.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), book.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void getBookById() {
        Book book = new Book(1L,"Test");
        log.info(book.toString());

        Book newBook = bookRepository.save(book);
        log.info(newBook.toString());
        Book responseBook = webTestClient.get()
                .uri("/book/" + newBook.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        log.info(responseBook.toString());
        Assertions.assertEquals(newBook.getId(),responseBook.getId());
        Assertions.assertEquals(newBook.getName(),responseBook.getName());
    }

    /**
     * Тест ручки POST
     */
    @Test
    void createBook() {
        Book book = new Book(1L,"Test Create");

        Book bookWeb = webTestClient.post()
                .uri("/book")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(bookWeb);
        Assertions.assertNotNull(bookWeb.getId());
        Assertions.assertEquals(bookWeb.getName(), book.getName());
        Assertions.assertTrue(bookRepository.findById(bookWeb.getId()).isPresent());
    }

    @Test
    void deleteBook() {
        log.info("Delete test");
        Book book = new Book(1L,"Test");
        book = bookRepository.save(book);
        log.info(book.toString());
        webTestClient.delete()
                .uri("/book/" + book.getId())
                .exchange()
                .expectStatus().isNoContent();
        Assertions.assertNull(bookService.getBookById(book.getId()));
    }
}