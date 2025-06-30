package ru.gb.springdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ReaderControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ReaderService readerService;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    IssueRepository issueRepository;

    @Test
    void getAllReader() {
        readerRepository.saveAll(List.of(new Reader("Test Reader 1"), new Reader("Test Reader 2")));
        List<Reader> readers = readerService.getAllReader();

        List<Reader> readersWeb = webTestClient.get()
                .uri("/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Reader>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(readersWeb.size(), readers.size());
        for (Reader reader : readersWeb) {
            boolean found = readers.stream()
                    .filter(it -> Objects.equals(it.getId(), reader.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), reader.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void getReaderById() {
        Reader reader = readerRepository.save(new Reader(1L, "Test Reader"));
        log.info(reader.toString());
        String uri = "/reader/" + reader.getId();
        log.info(uri);

        Reader testReader = webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(testReader);
        log.info(testReader.toString());
        Assertions.assertEquals(reader.getId(), testReader.getId());
        Assertions.assertEquals(reader.getName(), testReader.getName());
    }

    @Test
    void getIssuesByIdReader() {
        issueRepository.saveAll(List.of(
                new Issue(1L, 1L),
                new Issue(2L, 2L),
                new Issue(2L, 1L)));
        List<Issue> issues = issueRepository.findByReaderIdAndReturnedTimestamp(1L, null);
        log.info(issues.toString());

        List<Issue> booksWeb = webTestClient.get()
                .uri("/reader/1/issue")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Issue>>() {
                })
                .returnResult()
                .getResponseBody();
        assertEquals(booksWeb.size(), issues.size());
        for (Issue issue : booksWeb) {
            boolean found = issues.stream()
                    .filter(it -> Objects.equals(it.getBookId(), issue.getBookId()))
                    .anyMatch(it -> Objects.equals(it.getReaderId(), issue.getReaderId()));
            assertTrue(found);
        }
    }

    @Test
    void createReader() {
        Reader reader = new Reader(1L, "Test Create");
        log.info(reader.toString());

        Reader readerWeb = webTestClient.post()
                .uri("/reader")
                .bodyValue(reader)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(readerWeb);
        Assertions.assertNotNull(readerWeb.getId());
        Assertions.assertEquals(readerWeb.getName(), reader.getName());
        Assertions.assertTrue(readerRepository.findById(readerWeb.getId()).isPresent());
    }

    @Test
    void deleteReader() {
        Reader reader = new Reader(1L, "Test Reader");
        reader = readerRepository.save(reader);
        log.info(reader.toString());
        webTestClient.delete()
                .uri("/reader/" + reader.getId())
                .exchange()
                .expectStatus().isNoContent();
        assertNull(readerService.getReaderById(reader.getId()));
    }
}