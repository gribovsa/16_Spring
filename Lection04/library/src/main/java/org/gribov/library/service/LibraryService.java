package org.gribov.library.service;

import org.gribov.library.model.Book;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервисный слой, который будет служить “мостом”,
 * между нашим контроллером (который обрабатывает запросы),
 * и нашим репозиторием (который управляет данными).
 */
@Service
public class LibraryService {
    private final Library library = new Library();
    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }
    public Book getBookById(Long id) {
        return library.getBookById(id);
    }
    public Long addBook(Book book) {
        library.addBook(book);
        return book.getId();
    }
    public void deleteBook(Long id) {
        library.deleteBook(id);
    }
}
