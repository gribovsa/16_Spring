package org.gribov.homework02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс StudentController, который принимает внешние http запросы
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    /**
     * Конструктор, инжекция зависимости studentRepository
     */
    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Метод - получить список всех студентов
     * <a href="http://localhost:8080/student/all">...</a> -> List<Student> studentList
     *
     * @return список всех студентов
     */
    @GetMapping("/all")
    public List<Student> getStudentAll() {
        return studentRepository.getStudentAll();
    }

    /**
     * Метод - получить студента по ID
     * <a href="http://localhost:8080/student/id/1">...</a>
     *
     * @param id идентификатор студента
     * @return объект студент
     */
    @GetMapping(value = "/id/{id}")
    public Student getStudentByID(@PathVariable long id) {
        return studentRepository.getStudentID(id);
    }

    /**
     * Метод - получить список студентов, чье имя содержит подстроку studentName = Борис
     * <a href="http://localhost:8080/student/search?name=Борис">...</a>
     *
     * @param name имя студента
     * @return список студентов
     */
    @GetMapping(value = "/search")
    public List<Student> getStudentByName(@RequestParam String name) {
        return studentRepository.getStudentName(name);
    }

    /**
     * Метод - получить студентов группы
     * <a href="http://localhost:8080/student/group/ТФ-1-98">...</a>
     *
     * @param groupName имя группы
     * @return список студентов
     */
    @GetMapping(value = "/group/{groupName}")
    public List<Student> getStudentByGroupMame(@PathVariable String groupName) {
        return studentRepository.getStudentGroupName(groupName);
    }


    /**
     * Метод - создать студента
     * <a href="http://localhost:8080/student/create">...</a>
     * @param student объект студент в виде json строки
     * @return список студентов с учётом нового студента
     */
    @PostMapping(value = "/create")
    public List<Student> createStudent(@RequestBody Student student) {
        return studentRepository.createStudent(student);
    }


    /**
     * Метод - удалить студента по ID
     * <a href="http://localhost:8080/student/delete/6">...</a>
     *
     * @param id идентификатор студента
     * @return список студентов после удаления студента
     */

    @DeleteMapping(value = "/delete/{id}")
    public List<Student> deleteStudentByID(@PathVariable long id) {
        return studentRepository.deleteStudent(id);
    }

}
