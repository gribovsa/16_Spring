package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    /**
     * Метод стартует после инициализации конструктора
     */
    @PostConstruct
    public void generateData() {
        books.add(new Book("Война и мир"));
        books.add(new Book("Мёртвые души"));
        books.add(new Book("Чистый код"));
    }


    /**
     * Метод возвращает все книги
     */
    public List<Book> getAllBook() {
        return List.copyOf(books);
    }


    /**
     * Метод возвращает книгу по id
     */
    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }


    /**
     * Метод добавит книгу в репозиторий (если книга имеет оригинальный id и название)
     */
    public Book addBook(Book book) {
        if (books.stream().anyMatch(existBook -> existBook.getId().equals(book.getId()) ||
                existBook.getName().equals(book.getName()))) {
            //anyMatch возвращает true, если хоть один элемент потока удовлетворяют условию в предикате
            return null;
        } else {
            Book newBook = new Book(book.getName());
            books.add(newBook);
            return newBook;
        }
    }


    /**
     * Метод обновит книгу
     */
    public Book updateBook(Long id, Book updatebook) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .peek(book -> book.setName(updatebook.getName()))
                .findFirst()
                .orElse(null);
    }


    /**
     * Метод удалит книгу
     */
    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
