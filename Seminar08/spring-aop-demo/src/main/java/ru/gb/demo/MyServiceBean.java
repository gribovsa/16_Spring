package ru.gb.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyServiceBean {

  public String method1(String arg) {
    System.out.println(getClass().getName());
    return method2(arg);
  }

  //  @Transactional
  public String method2(String arg) {
    System.out.println(getClass().getName());
    return arg;
  }

  public String method3(String arg) {
    throw new RuntimeException("errrr");
  }
}
