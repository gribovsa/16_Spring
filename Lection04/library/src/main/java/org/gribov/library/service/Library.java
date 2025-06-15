package org.gribov.library.service;

import org.gribov.library.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Library, которая будет действовать как
 * репозиторий
 */
public class Library {

    private Long idCounter = 1L;

    private final List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {

        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        book.setId(idCounter++);
        books.add(book);
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
