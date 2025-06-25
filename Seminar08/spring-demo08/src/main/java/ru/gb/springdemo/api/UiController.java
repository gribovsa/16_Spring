package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/ui")
public class UiController {
    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;
    @Autowired
    IssuerService issuerService;

    /**
     * Результат доступен по ссылке
     * <a href="http://localhost:8080/ui/books">...</a>
     */
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * Результат доступен по ссылке
     * <a href="http://localhost:8080/ui/readers">...</a>
     */
    @GetMapping("/readers")
    public String getAllReader(Model model) {
        List<Reader> readers = readerService.getAllReader();
        model.addAttribute("readers", readers);
        return "readers";
    }

    /**
     * Результат доступен по ссылке
     * <a href="http://localhost:8080/ui/issues">...</a>
     */
    @GetMapping("/issues")
    public String getAllIssue(Model model) {
        model.addAttribute("issues", issuerService.getAllIssue());
        return "issues";
    }

    /**
     * Результат доступен по ссылке
     * <a href="http://localhost:8080/ui/readers/1">...</a>
     * <a href="http://localhost:8080/ui/readers/2">...</a>
     * <a href="http://localhost:8080/ui/readers/3">...</a>
     */
    @GetMapping("/readers/{id}")
    public String getIssueById(Model model, @PathVariable Long id) {
        Reader reader = readerService.getReaderById(id);
        if (reader == null) {
            throw new NoSuchElementException("читатель не найден");
        }
        List<Issue> issues = issuerService.getIssuesByIdReader(id);
        model.addAttribute("id", reader.getId());
        model.addAttribute("name", reader.getName());
        model.addAttribute("issues", issues);
        return "readerId";
    }
}
