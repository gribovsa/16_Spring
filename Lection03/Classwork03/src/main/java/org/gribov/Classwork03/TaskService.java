package org.gribov.classwork03;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Следующим шагом будет создание сервиса, который будет управлять нашими
 * задачами. Мы используем простой список для хранения задач.
 */
@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Метод показать все задачи
     * @return возвращает лист задач
     */
    public List<Task> getAllTasks() {
        return tasks;
    }


    /**
     * Метод показать задачу по id
     * @param id получает идентификатор задачи
     * @return возвращает объект задачу
     */
    public Task getTask(UUID id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    /**
     * Метод создать задачу
     * @param task получает объект задачу
     * @return возвращает объект задачу
     */
    public Task createTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Метод обновить задачу
     * @param id получает идентификатор задачи
     * @param updatedTask получает обновлённый объект - задачу который прислал клиент
     * @return возвращает существующую изменённую задачу
     */
    public Task updateTask(UUID id, Task updatedTask) {
        Task existingTask = getTask(id); //вызываем созданный метод getTask
        if (existingTask != null) {
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setCompletionTime(updatedTask.getCompletionTime());
        }
        return existingTask;
    }

    /**
     * Метод удалить задачу
     * @param id получает id задачи
     */
    public void deleteTask(UUID id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

}
