package ru.gb.springdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Класс книга
 */
@Data
public class Book {

    public static long sequence = 1L;
    private final Long id;

    private String name;

    public Book(String name) {
        this.id = sequence++;
        this.name = name;
    }

}
