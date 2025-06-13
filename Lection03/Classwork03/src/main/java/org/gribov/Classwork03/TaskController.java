package org.gribov.classwork03;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Теперь давайте создадим контроллер, который будет обрабатывать HTTP-запросы и
 * использовать наш сервис для выполнения операций над задачами.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    // внедрение зависимостей через конструктор
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * Метод getAllTasks обрабатывает запрос клиента GET
     * @return Чтобы контролировать другие аспекты ответа,
     * такие как HTTP-статус, мы можем использовать класс `ResponseEntity`
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);

    }

    /**
     * Метод getTask обрабатывает запрос клиента GET
     * @param id получаем идентификатор
     * @return  используем класс `ResponseEntity`, возвращаем объект task и статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable("id") UUID id) {
        Task task = taskService.getTask(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Метод createTask обрабатывает запрос клиента POST
     * @param newTask получает объект в виде json строки
     * @return используем класс `ResponseEntity`, вызываем метод taskService.createTask
     * и возвращаем статус
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        return new ResponseEntity<>(taskService.createTask(newTask),
                HttpStatus.CREATED);
    }


    /**
     * Метод updateTask обрабатывает запрос клиента PUT
     * @param id получает id
     * @param updatedTask получает объект
     * @return используем класс `ResponseEntity`, возвращаем объект task и статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @RequestBody
    Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Метод deleteTask обрабатывает запрос клиента DELETE
     * @param id получает id
     * @return используем класс `ResponseEntity`, возвращаем статус
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
