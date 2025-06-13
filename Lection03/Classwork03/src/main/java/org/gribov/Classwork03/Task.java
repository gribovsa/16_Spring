package org.gribov.classwork03;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

//@Data
/*
@Data дает нам геттеры для всех полей,
сеттеры для всех не финальных полей,
правильные реализации toString, equals и hashCode, охватывающие все поля класса,
а также конструктор для всех финальных полей.
 */
/**
 * Начнем с создания класса Task, который будет представлять нашу задачу.
 */
@Setter
@Getter
public class Task {

    public enum Status{
        TO_DO,
        IN_PROGRESS,
        DONE
    }


    private UUID id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime completionTime;

    public Task(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }
}
