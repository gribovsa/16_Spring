package ru.gb.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;
import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private IssuerService issuerService;


    /**
     * Метод возвращает список всех читателей
     */
    public List<Reader> getAllReader() {
        return readerRepository.findAll();
    }

    /**
     * Метод ищет и возвращает читателя по id
     */
    public Reader getReaderById(long id) {
        return readerRepository.findById(id).orElse(null);
    }

    /**
     * Метод добавляет читателя
     */
    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    /**
     * Метод обновляет читателя
     */
    public Reader updateReaderById(Long id, Reader reader) {
        Reader updateReader = getReaderById(id);
        updateReader.setName(reader.getName());
        return readerRepository.save(updateReader);
    }

    /**
     * Метод удаляет читателя
     */
    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }



}
