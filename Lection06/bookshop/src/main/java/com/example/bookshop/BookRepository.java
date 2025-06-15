package com.example.bookshop;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Здесь:
 * ● BookRepository: Это имя нашего репозитория. Обычно оно базируется на
 * имени соответствующего класса модели с добавлением суффикса
 * “Repository”.
 * ● extends JpaRepository: Здесь мы говорим, что наш репозиторий должен
 * расширять JpaRepository, что дает нам доступ к ряду готовых методов для
 * работы с базой данных, таких как findAll(), findById(), save(), deleteById(), и
 * других.
 * ● Book, Long: Это параметры типа для JpaRepository. Book - это класс сущности,
 * с которым будет работать репозиторий, а Long - тип идентификатора этой
 * сущности.
 */
public interface BookRepository extends JpaRepository<Book,Long> {
    /*
        Итак, в нашем репозитории у нас уже есть базовые CRUD-операции. В случае
    необходимости выполнить более сложные запросы, мы можем добавить в
    репозиторий собственные методы.
     */
}
