package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reader")
@Tag(name = "Читатели")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private IssuerService issuerService;


    //ручки
    @GetMapping
    @Operation(summary = "Get all readers", description = "Загружает список всех читателей")
    public ResponseEntity<List<Reader>> getAllReader() {
        List<Reader> readers = readerService.getAllReader();
        log.info(!readers.isEmpty() ? readers.toString() : "none");
        return !readers.isEmpty()
                ? new ResponseEntity<>(readers, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get reader by id", description = "Загружает читателя с указанным идентификатором читателя")
    public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
        Reader reader = readerService.getReaderById(id);
        log.info(reader.toString());
        return reader != null
                ? new ResponseEntity<>(reader, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/issue")
    @Operation(summary = "Get all issues by reader id", description = "Загружает список фактов выдач книг, для читателя с указанным идентификатором")
    public ResponseEntity<List<Issue>> getIssuesByIdReader(@PathVariable Long id) {
        List<Issue> readerIssue;
        readerIssue = issuerService.getIssuesByIdReader(id);
        return !readerIssue.isEmpty()
                ? new ResponseEntity<>(readerIssue, HttpStatus.OK)
                : ResponseEntity.notFound().build();

    }

    @PostMapping
    @Operation(summary = "Create new reader", description = "Создаёт нового читателя")
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        Reader newReader = readerService.addReader(reader);
        return newReader != null
                ? new ResponseEntity<>(newReader, HttpStatus.CREATED)
                : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reader by id", description = "Удаляет читателя с указанным идентификатором")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReaderById(id);
        return ResponseEntity.noContent().build();
    }
}
