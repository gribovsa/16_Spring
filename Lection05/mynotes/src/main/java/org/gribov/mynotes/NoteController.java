package org.gribov.mynotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    //Передаём интерфейс методы которого реализованы в спец классе сервиса NoteServiceImpl
    private final NoteService service;

    /**
     * Конструктор
     */
    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }


    /* Методы для работы с http запросами.
    Каждый из этих методов соответствует определенному типу HTTP-запроса
    (GET, POST, PUT, DELETE) и обрабатывает определенный тип операции.
    */

    @GetMapping
    public List<Note> getAllNotes() {
        return service.getAllNotes();
    }
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return service.getNoteById(id);
    }
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return service.createNote(note);
    }
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) {
        return service.updateNote(id, note);
    }
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
    }

}
