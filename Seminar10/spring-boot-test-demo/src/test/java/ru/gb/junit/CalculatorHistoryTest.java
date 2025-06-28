package ru.gb.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gb.junit.CalculatorHistory;
import ru.gb.junit.Storage;

/**
 * Класс - тест на класс CalculatorHistory
 */
class CalculatorHistoryTest {

  @Test
  void testLogSumOperation() {
    Storage mock = Mockito.mock(Storage.class);
    //передаём проверяемому классу наш Storage, через Мок
    CalculatorHistory calculatorHistory = new CalculatorHistory(mock);
    //если был вызов storage.save
    Mockito.when(mock.save(Mockito.anyString())).thenReturn(true);

    Assertions.assertDoesNotThrow(() -> calculatorHistory.logSumOperation(1, 1, 2));
  }

  @Test
  void testLogSumOperationRuntimeException() {
    Storage mock = Mockito.mock(Storage.class);
    CalculatorHistory calculatorHistory = new CalculatorHistory(mock);
    Mockito.when(mock.save("1 + 1 = 2")).thenReturn(false);

    Assertions.assertThrows(RuntimeException.class, () -> calculatorHistory.logSumOperation(1, 1, 2));
  }


}