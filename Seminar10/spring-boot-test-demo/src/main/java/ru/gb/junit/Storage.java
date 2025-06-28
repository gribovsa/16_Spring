package ru.gb.junit;

/**
 * Интерфейс, который принимает какую либо строку, сохраняет её
 * и возвращает, смог ли её сохранить или нет
 */
public interface Storage {

  boolean save(String text);

}
