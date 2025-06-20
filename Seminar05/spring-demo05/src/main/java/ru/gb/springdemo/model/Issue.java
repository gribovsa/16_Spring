package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс - запись о факте выдачи книги
 */
@Data
@Entity
@Table(name = "issue")
public class Issue {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "reader_id")
    private Long readerId;


    //Дата выдачи
    private LocalDateTime timestamp;
    //Дата возврата
    private LocalDateTime returnedTimestamp;

    //Для работы с сущностью обязателен конструктор без аргументов
    public Issue() {
    }

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }

}
