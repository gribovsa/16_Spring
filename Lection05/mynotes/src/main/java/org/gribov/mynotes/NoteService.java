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
    List<Note> getAllNotes();

    /**
     * Получить заметку по ID
     */
    Note getNoteById(Long id);

    /**
     * Создать новую заметку
     */
    Note createNote(Note note);

    /**
     * Обновить существующую заметку
     */
    Note updateNote(Long id, Note note);

    /**
     * Удалить заметку
     */
    void deleteNote(Long id);
}
