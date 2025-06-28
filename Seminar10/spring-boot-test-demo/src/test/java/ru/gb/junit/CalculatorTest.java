package ru.gb.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gb.junit.Calculator;
import ru.gb.junit.CalculatorHistory;

/**
 * Тест класса Calculator
 */
class CalculatorTest {

  @Test //обязательная аннотация
  void testSum() { //должен быть void

    //создаём Мок, чтобы понять, что происходит корректное сохранение операций суммирования
    CalculatorHistory calculatorHistoryMock = Mockito.mock(CalculatorHistory.class);

    //вызываем проверяемый класс, передаём ему в качестве storage этот Мок
    Calculator calculator = new Calculator(calculatorHistoryMock);

    int sum = calculator.sum(2, 7); //его метод sum
    Assertions.assertEquals(9, sum); //здесь класс Assertions - наше ожидание
    //далее запускаем наш тест - class CalculatorTest, метод void testSum()

    //Чтобы проверить, что делает этот Мок
    Mockito.verify(calculatorHistoryMock).logSumOperation(2, 7, 9);
  }

}