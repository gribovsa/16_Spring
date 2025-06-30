package ru.gb.springdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.IssuerService;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class IssuerControllerTest extends JUnitSpringBootBase{
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    IssueRepository issuesRepository;
    @Autowired
    IssuerService issuerService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;

    @Test
    void issueBook() {
        log.info("Create test");
        IssueRequest issueRequest = new IssueRequest(100L, 100L);
        log.info(issueRequest.toString());
        webTestClient.post()
                .uri("/issue")
                .bodyValue(issueRequest)
                .exchange()
                .expectStatus().isNotFound();

        Book book = bookRepository.save(new Book("Test Book"));
        Reader reader = readerRepository.save(new Reader("Test Reader"));
        issueRequest.setBookId(book.getId());
        issueRequest.setReaderId(reader.getId());
        log.info(issueRequest.toString());

        Issue issueWeb = webTestClient.post()
                .uri("/issue")
                .bodyValue(issueRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Issue.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(issueWeb);
        log.info(issueWeb.toString());
        Assertions.assertNotNull(issueWeb.getId());
        Assertions.assertEquals(issueWeb.getReaderId(), issueRequest.getReaderId());
        Assertions.assertEquals(issueWeb.getBookId(), issueRequest.getBookId());
        assertTrue(issuesRepository.findById(issueWeb.getId()).isPresent());
    }

    @Test
    void getInfoIssueById() {
        log.info("Find by id test");
        Book book = bookRepository.save(new Book("Test Book"));
        Reader reader = readerRepository.save(new Reader("Test Reader"));
        Issue issue = new Issue(book.getId(), reader.getId());
        issue = issuesRepository.save(issue);
        log.info(issue.toString());

        Issue issueWeb = webTestClient.get()
                .uri("/issue/" + issue.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(issueWeb);
        log.info(issueWeb.toString());
        Assertions.assertEquals(issue.getId(), issueWeb.getId());
        Assertions.assertEquals(issue.getBookId(), issueWeb.getBookId());
        Assertions.assertEquals(issue.getReaderId(), issueWeb.getReaderId());
    }

    @Test
    void returnedIssue() {
        Issue issue = new Issue(1L,1L);
        issue = issuesRepository.save(issue);
        log.info(issue.toString());

        Issue issueWeb = webTestClient.put()
                .uri("/issue/" + issue.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(issueWeb);
        Assertions.assertEquals(issue.getId(), issueWeb.getId());
        Assertions.assertEquals(issue.getBookId(), issueWeb.getBookId());
        Assertions.assertEquals(issue.getReaderId(), issueWeb.getReaderId());
        Assertions.assertNotNull(issueWeb.getReturnedTimestamp());
    }
}