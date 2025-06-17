package com.example.bookshop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
 * ● @Getters and @Setters: Это методы, которые позволяют нам получать и
 * устанавливать значения полей класса, созданы используя lombok.
 * ● @AllArgsConstructor - Создание конструктора, включающим все не финальные поля. Используя lombok.
 * ● @NoArgsConstructor - Создания конструктора без аргументов. Опять же lombok.
 *
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }
}
