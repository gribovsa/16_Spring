package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    /**
     * Метод создаёт и сохраняет факт выдачи книги
     */
    public Issue save(Issue issue) {
        issues.add(issue);
        return issue;
    }

    /**
     * Метод возвращает копию списка всех фактов выдачи
     */
    public List<Issue> getAllIssues() {
        return List.copyOf(issues);
    }

    /**
     * Метод возвращает факт выдачи по id
     */
    public Issue getIssueById(long id) {
        return issues.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод возвращает факты выдачи по id читателя (только если книга не возвращена)
     */
    public List<Issue> getIssuesByIdReader(Long id) {
        return issues.stream()
                .filter(issue -> issue.getReaderId().equals(id))
                .filter(issue -> issue.getReturnedTimestamp() == null)
                .toList();
    }

    /**
     * Метод закрывает факт выдачи книги
     * записывает текущее время в поле возврата книги ReturnedTimestamp
     * @param id получает id объекта Issue
     * @return объект Issue c изменённым полем ReturnedTimestamp
     */
    public Issue setReturnedIssue(Long id) {
        return issues.stream()
                .filter(issue -> issue.getId().equals(id))
                .peek(issue -> issue.setReturnedTimestamp(LocalDateTime.now()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод возвращает список закрытых фактов выдачи (т.е случаи если книга возвращена)
     */
    public List<Issue> getListReturnedIssue() {
        return issues.stream()
                .filter(issue -> issue.getReturnedTimestamp() != null)
                .toList();
    }
}
