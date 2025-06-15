package com.example.bookshop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель книги
 * Здесь:
 * ● @Entity: Это аннотация говорит Spring, что данный класс является
 * сущностью, которую нужно учесть при создании схемы базы данных.
 * ● @Id и @GeneratedValue: Эти аннотации указывают, что поле id является
 * уникальным идентификатором (ключом) для каждой книги и что его значение
 * будет автоматически генерироваться.
 * ● private Long id; private String title; private String author; private String isbn;
 * private int publicationYear; Это поля нашего класса. Каждое поле
 * представляет собой атрибут, который мы хотим хранить для каждой книги.
 * 15
 * ● getters and setters: Это методы, которые позволяют нам получать и
 * устанавливать значения полей класса, созданы используя аннотации (lombok)
 */

@Setter
@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

}
