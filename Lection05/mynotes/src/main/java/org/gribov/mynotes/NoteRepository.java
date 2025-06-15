package org.gribov.mynotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий NoteRepository, который
 * будет обеспечивать операции CRUD для нашего класса NoteEntity
 * В наследуемом интерфейсе мы указываем два параметра типа для JpaRepository: тип нашей
 * сущности (NoteEntity) и тип идентификатора (Long).
 */

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    /*
        JpaRepository предоставляет нам множество полезных методов,
    таких как findAll(), findById(), save(), delete(), и т.д., без необходимости их реализовывать.
     */

    /**
     * Метод для поиска заметок по автору.
     * В JpaRepository, мы можем добавить свои собственные методы.
     * Мы просто определили метод с именем findByAuthor, и Spring Data автоматически
     * предоставит его реализацию для нас.
     */
    List<NoteEntity> findByAuthor(String author);

}
