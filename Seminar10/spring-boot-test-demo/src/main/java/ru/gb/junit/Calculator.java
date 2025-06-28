package ru.gb.junit;

/**
 * Первый пример, с самым простым классом - калькулятор.
 * Чтобы создать тест - на имени класса Alt+Insert выбрать Test.
 * Будет создан в папке test.ru.gb файл CalculatorTest
 */
public class Calculator {

  private final CalculatorHistory calculatorHistory;

  public Calculator(CalculatorHistory calculatorHistory) {
    this.calculatorHistory = calculatorHistory;
  }

  public int sum(int a, int b) {
    int result = a + b;
    calculatorHistory.logSumOperation(a, b, result);
    return result;
  }

}
