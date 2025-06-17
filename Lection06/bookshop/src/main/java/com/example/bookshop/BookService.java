package com.example.bookshop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный класс BookService, который будет содержать
 * бизнес-логику нашего приложения.
 * ● @Service: Это аннотация Spring, которая указывает, что данный класс
 * является сервисом. Это помогает Spring определить этот класс при
 * сканировании компонентов.
 * ● private final BookRepository bookRepository; Здесь мы инжектируем наш
 * репозиторий в сервис. Это позволяет нам использовать методы репозитория
 * внутри сервиса.
 * ● @RequiredArgsConstructor //это lombok, сделает конструктор с полем final bookRepository
 * ● public List findAll(); public Optional findById(Long id); public Book save(Book
 * book); public void deleteById(Long id); Это наши методы сервиса. Каждый из
 * этих методов обращается к соответствующему методу репозитория для
 * выполнения операции и возвращает результат.
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    // Метод поиска книги по ID. Optional - означает, что не факт что найдётся
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    /*
        Таким образом, слой сервисов выступает как посредник между контроллерами и
    репозиториями, обеспечивая место для бизнес-логики нашего приложения.
     */
}
