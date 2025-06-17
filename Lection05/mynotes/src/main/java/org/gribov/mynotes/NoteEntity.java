package org.gribov.mynotes;

import jakarta.persistence.*;

/**
 * Класс NoteEntity для работы с базой данных (ждя создания базы данных)
 * (классы Note и NoteEntity принято разделять)
 */
// @Entity: эта аннотация указывает, что класс является JPA сущностью.
@Entity

// @Table: эта аннотация позволяет нам указать имя таблицы, на которую будет
// отображаться наш класс.
@Table(name = "notes")
public class NoteEntity {

    /*
        @Id и @GeneratedValue: эти аннотации указывают, что поле id является
    идентификатором и его значение должно быть сгенерировано
    автоматически.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // поле обязательно для заполнения nullable = false
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    // поле с ограничением длинны
    @Column(nullable = false, length = 2000)
    private String content;

    // для работы с сущностью необходим пустой конструктор
    public NoteEntity() {
    }



    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
