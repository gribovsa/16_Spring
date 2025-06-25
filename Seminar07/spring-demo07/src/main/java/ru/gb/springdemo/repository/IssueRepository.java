package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    /**
     * Метод - название метода написано в соответствии с конвенцией.
     * JPA Repository methods convention — это соглашение об именовании методов репозиториев
     * в Spring Data JPA, которое позволяет определять методы запросов без написания
     * явных запросов SQL или JPQL.
     * Метод использую для поиска фактов выдачи по id пользователя и времени возврата равного null (т.е книга у него на руках)
     * @param readerId - поле есть у сущности Issue
     * @param returnedTime - поле есть у сущности Issue
     * @return возвращает список фактов
     */
    List<Issue> findByReaderIdAndReturnedTimestamp(Long readerId, LocalDateTime returnedTime);

    /**
     * Аналогичный метод, только возвращает все факты выдачи по id пользователя
     * @param readerId идентификатор пользователя (поле у сущности Issue
     * @return список фактов
     */
    List<Issue> findByReaderId(Long readerId);
}

/*
Примеры методов (чтобы не забыть суть конвенции)
Некоторые примеры методов, которые следуют соглашению именования JPA Repository:
List<User> findByFirstName(String firstName) — поиск пользователей по имени.
List<User> findByAgeGreaterThan(int age) — поиск пользователей старше определённого возраста.
List<User> findByEmailContaining(String emailPart) — поиск пользователей, email которых содержит подстроку.
 */