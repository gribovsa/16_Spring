package ru.gb.springdemo.service;

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
        return bookRepository.getAllBook();

    }

    /**
     * Метод ищет книгу по id
     * @param id  получает идентификатор книги
     * @return возвращает книгу
     */
    public Book getBookById(long id) {
        return bookRepository.getBookById(id);

    }

    /**
     * Метод добавляет книгу
     * @param book получает объект книга
     * @return возвращает книгу
     */
    public Book addBook(Book book) {
        return bookRepository.addBook(book);

    }

    /**
     * Метод обновляет книгу
     * @param id получает идентификатор
     * @param book получает объект книга
     * @return возвращает книгу
     */
    public Book updateBookById(Long id, Book book) {
        return bookRepository.updateBook(id, book);
    }

    /**
     * Метод удаляет книгу
     * @param id получает идентификатор книги
     */
    public void deleteBookById(Long id) {
        bookRepository.deleteBook(id);

    }

}
