package org.gribov.homework02;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> studentList;

    public StudentRepository() {

        this.studentList = new ArrayList<>();

        studentList.add(new Student("Алексей", "ТФ-1-98"));
        studentList.add(new Student("Борис", "ТФ-1-98"));
        studentList.add(new Student("Василий", "ТФ-2-98"));
        studentList.add(new Student("Григорий", "ТФ-2-98"));
        studentList.add(new Student("Борис", "ТФ-3-98"));
        studentList.add(new Student("Алексей", "ТФ-3-98"));

    }

    /**
     * Получить всех студентов
     */
    public List<Student> getStudentAll() {
        return List.copyOf(studentList);
    }

    /**
     * Получить студента по ID
     */
    public Student getStudentID(long id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * Получить список студентов, чье имя содержит подстроку studentName
     */
    public List<Student> getStudentName(String name) {
        List<Student> resultList = new ArrayList<>();
        for (Student student : studentList) {
            if (name.equals(student.getName())) {
                resultList.add(student);
            }
        }
        return resultList;
    }

    /**
     * Получить всех студентов группы
     */

    public List<Student> getStudentGroupName(String groupName) {
        List<Student> resultList = new ArrayList<>();
        for (Student student : studentList) {
            if (groupName.equals(student.getGroupName())) {
                resultList.add(student);
            }
        }
        return resultList;
    }

    /**
     * Создать студента
     */
    public List<Student> createStudent(Student student) {
        studentList.add(student);
        return List.copyOf(studentList);
    }


    /**
     * Удалить студента
     */
    public List<Student> deleteStudent(long id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                break;
            }
        }
        return List.copyOf(studentList);
    }
}
