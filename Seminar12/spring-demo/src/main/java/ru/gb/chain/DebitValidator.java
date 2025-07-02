package ru.gb.chain;

import java.math.BigInteger;

/**
 * Интерфейс проверок
 */
public interface DebitValidator {

  /**
   * Метод проверок
   */
  void validate(Account account, BigInteger amount) throws DebitValidationException;

  boolean isApplicable(Account account);

}
