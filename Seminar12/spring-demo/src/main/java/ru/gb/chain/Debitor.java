package ru.gb.chain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Класс отвечающий за списание - банковская операция
 */
@Component
@RequiredArgsConstructor
public class Debitor {

  // chain of responsibility
  //spring автоматически ищет все бины (класс AccountUnderArrestValidator) из списка
  private final List<DebitValidator> validators;

  public void debit(Account account, BigInteger amount) {
    try {
      for (DebitValidator validator : validators) {
        if (validator.isApplicable(account)) {
          validator.validate(account, amount);
        }
      }
    } catch (DebitValidationException e) {
      // операция запрещена
      return;
    }

    doDebit(account, amount);
  }

  private void doDebit(Account account, BigInteger amount) {
    // списываем
  }


//  private void checkAccountBlocked(Account account) throws DebitValidationException {
//    // проверяем, что счет не заблокирован
//  }
//
//  private void checkAmountAvailable(Account account, BigInteger amount) throws DebitValidationException {
//    // проверяем, что деньги есть
//  }


}
