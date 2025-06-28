package ru.gb.junit;

/**
 * Класс, который будет сохранять историю запуска калькулятора
 */
public class CalculatorHistory {

  private final Storage storage;

  /**
   * Конструктор принимает интерфейс storage
   */
  public CalculatorHistory(Storage storage) {
    this.storage = storage;
  }

  /**
   * Метод сохранения истории операции суммирования калькулятора
   */
  public void logSumOperation(int a, int b, int result) {
    if (!storage.save(String.format("%s + %s = %s", a, b, result))) {
      //если не удалось сохранить, то выбрасываем exception
      throw new RuntimeException("can't log sum operation");
    }
  }

}
