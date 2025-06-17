package org.gribov.mynotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Создадим класс NoteServiceImpl, который будет реализовывать наш
 * интерфейс NoteService
 */
@Service
public class NoteServiceImpl implements NoteService {

    //Инжекция зависимости
    private final NoteRepository repository;

    /**
     * Конструктор
     */
    @Autowired
    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }


    //Реализация методов

    @Override
    public List<NoteEntity> getAllNotes() {
        return repository.findAll();
    }

    @Override
    public NoteEntity getNoteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public NoteEntity createNote(NoteEntity note) {
        return repository.save(note);
    }

    @Override
    public NoteEntity updateNote(Long id, NoteEntity note) {
    // мы должны сначала проверить, существует ли заметка с данным ID
        NoteEntity existingNote = getNoteById(id);
    // обновляем поля существующей заметки
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
    // сохраняем и возвращаем обновленную заметку
        return repository.save(existingNote);
    }

    @Override
    public void deleteNote(Long id) {
    // проверяем, существует ли заметка с данным ID
        getNoteById(id);
    // если да, то удаляем ее
        repository.deleteById(id);
    }

}
