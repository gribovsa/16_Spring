package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new Reader("Алексей"),
      new Reader("Сергей"),
      new Reader("Василий")
    ));
  }

    public List<Reader> getAllReader () {
    return List.copyOf(readers);
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public Reader addReader(Reader reader) {
    if (readers.stream().anyMatch(existReader -> existReader.getName().equals(reader.getName())
                                              || existReader.getId().equals(reader.getId()))) {
      return null;
    } else {
      Reader newReader = new Reader(reader.getName());
      readers.add(newReader);
      return newReader;
    }
  }

  public Reader updateReader(Long id, Reader updateReader) {
    return readers.stream()
            .filter(reader -> reader.getId().equals(id))
            .peek(reader -> reader.setName(updateReader.getName()))
            .findFirst()
            .orElse(null);
  }


    public void deleteReaderById(Long id) {
    readers.removeIf(book -> book.getId().equals(id));
  }
}
