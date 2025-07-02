package ru.gb.chain;

/**
 * Класс - исключение, которое может кинуть любая из проверок банк операции
 */
public class DebitValidationException extends Exception {

  public DebitValidationException() {
  }

  public DebitValidationException(String message) {
    super(message);
  }
}
