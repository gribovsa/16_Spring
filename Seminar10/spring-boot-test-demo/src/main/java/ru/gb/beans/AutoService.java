package ru.gb.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Бин - автосервис,  который будет менять колёса
 */
@Component
@RequiredArgsConstructor
public class AutoService {

  private final Auto auto;

  public boolean changeWheelsIn() {
    try {
      //если колёса можно поменять
      auto.changeWheels();
      return true;
    } catch (Exception e) {
      //если нет то выброс исключения
      return false;
    }
  }

}
