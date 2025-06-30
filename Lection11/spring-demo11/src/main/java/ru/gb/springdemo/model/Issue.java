package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс - запись о факте выдачи книги
 */
@Data
@Entity
@Table(name = "issue")
@Schema(name = "Факт выдачи книг")
public class Issue {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;
    @Column(name = "book_id")
    @Schema(name = "ID книги")
    private Long bookId;
    @Column(name = "reader_id")
    @Schema(name = "ID читателя")
    private Long readerId;


    //Дата выдачи
    @Schema(name = "Дата выдачи")
    private LocalDateTime timestamp;
    //Дата возврата
    @Schema(name = "Дата возврата")
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

    public Issue(Long id, Long bookId, Long readerId, LocalDateTime timestamp, LocalDateTime returnedTimestamp) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = timestamp;
        this.returnedTimestamp = returnedTimestamp;
    }
}
