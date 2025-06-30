package ru.gb.springdemo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    /**
     * Метод возвращает все книги
     * @return возвращает список книг
     */
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    /**
     * Метод ищет книгу по id
     * @param id получает идентификатор книги
     * @return возвращает книгу
     */
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);

    }

    /**
     * Метод добавляет книгу
     * @param book получает объект книга
     * @return возвращает книгу
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);

    }

    /**
     * Метод обновляет книгу
     * @param id   получает идентификатор
     * @param book получает объект книга
     * @return возвращает книгу
     */
    public Book updateBookById(Long id, Book book) {
        Book updateBook = getBookById(id);
        updateBook.setName(book.getName());
        return bookRepository.save(updateBook);
    }

    /**
     * Метод удаляет книгу
     * @param id получает идентификатор книги
     */
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);

    }

}
