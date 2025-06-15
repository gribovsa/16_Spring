package org.gribov.mynotes;

import jakarta.persistence.*;

//@Entity: эта аннотация указывает, что класс является JPA сущностью.
@Entity

//@Table: эта аннотация позволяет нам указать имя таблицы, на которую будет
//отображаться наш класс.
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

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

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
