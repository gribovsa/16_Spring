package ru.gb.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.gb.springdemo.aspect.Timer;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Member;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.repository.UsersRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class Application {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private UsersRepository usersRepository;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Timer
	@EventListener(ApplicationReadyEvent.class)
	public void generate(){

		bookRepository.save(new Book(null, "Дети Арбата"));
		bookRepository.save(new Book(null, "Война и Мир"));
		bookRepository.save(new Book(null, "Эра милосердия"));

		readerRepository.save(new Reader(null,"Иванов"));
		readerRepository.save(new Reader(null,"Петров"));
		readerRepository.save(new Reader(null,"Сидоров"));

		issueRepository.save(new Issue(null,1L,1L, LocalDateTime.now(),null));
		issueRepository.save(new Issue(null,2L,1L, LocalDateTime.now(ZoneId.of("Europe/Paris")),LocalDateTime.now(ZoneId.of("+05:00"))));
		issueRepository.save(new Issue(null,3L,3L, LocalDateTime.now(ZoneId.of("America/Puerto_Rico")),null));

		usersRepository.save(new Member(null,"admin", "admin", "admin"));
		usersRepository.save(new Member(null,"mem", "1234", "reader"));
		usersRepository.save(new Member(null,"user", "user", "reader"));
	}

}
