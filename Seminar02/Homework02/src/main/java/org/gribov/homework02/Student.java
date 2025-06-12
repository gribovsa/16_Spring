package org.gribov.homework02;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Класс студент
 */
public class Student {
    private static long idCounter = 1L;
    private long id;
    private String name;
    private String groupName;

    @JsonCreator
    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }

    //region геттеры
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroupName() {
        return groupName;
    }
    // endregion


    //region сеттеры
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    //endregion
}
