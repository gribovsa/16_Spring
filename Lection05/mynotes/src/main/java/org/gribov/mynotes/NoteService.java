package org.gribov.mynotes;

import java.util.List;

/**
 * Создадим интерфейс для нашего сервиса
 */
public interface NoteService {
    /*
        Здесь мы определяем пять основных операций, которые мы хотим выполнять над
    нашими заметками: получить все заметки, получить заметку по ID, создать новую
    заметку, обновить существующую заметку и удалить заметку.
     */

    /**
     * Получить все заметки
     */
    List<NoteEntity> getAllNotes();

    /**
     * Получить заметку по ID
     */
    NoteEntity getNoteById(Long id);

    /**
     * Создать новую заметку
     */
    NoteEntity createNote(NoteEntity note);

    /**
     * Обновить существующую заметку
     */
    NoteEntity updateNote(Long id, NoteEntity note);

    /**
     * Удалить заметку
     */
    void deleteNote(Long id);
}
