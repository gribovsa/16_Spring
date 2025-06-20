package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс - читатель
 */
@Data
@Entity
@Table(name = "reader")
public class Reader {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    //Для работы с сущностью обязателен конструктор без аргументов
    public Reader() {
    }

    public Reader(String name) {
        this.id = sequence++;
        this.name = name;
    }

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
