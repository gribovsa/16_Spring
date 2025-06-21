package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Класс книга
 */
@Data
@Entity
@Table(name = "book")
@Schema(name = "Книга")
public class Book {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Название")
    private String name;

    //Для работы с сущностью обязателен конструктор без аргументов
    public Book() {
    }

    public Book(String name) {
        this.id = sequence++;
        this.name = name;
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
