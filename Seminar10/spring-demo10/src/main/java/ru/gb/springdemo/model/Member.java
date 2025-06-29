package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс пользователь
 */
@Entity
@Table(name = "users")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pass")
    private String pass;
    @Column(name = "roles")
    private String roles;

    public Member() {
    }

    public Member(Long id, String name, String pass, String roles) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.roles = roles;
    }
}

