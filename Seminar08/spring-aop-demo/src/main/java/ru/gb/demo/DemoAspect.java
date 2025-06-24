package ru.gb.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class DemoAspect {


  /**
   * Бин pointcut - точка среза - то куда мы встраиваем наш бин совет Advice
   */
  @Pointcut("execution(* ru.gb.demo.MyServiceBean.*(..))")
  public void myServiceBeanMethodsPointcut() {
  }


  /**
   * Бин Advice - совет
   * в аргументе аннотации указывается аргумент pointcut, у нас это имя метода
   * В аргументе метода указывается JoinPoint - точка присоединения данного совета Advice.
   * Здесь нам доступны некоторые действия, которые мы можем совершить применительно к методу (к которому мы присоединились).
   */
  @Before("myServiceBeanMethodsPointcut()")
  public void before(JoinPoint joinPoint) {
    log.info("before sign = {}, args = {}", joinPoint.getSignature(), joinPoint.getArgs()[0]);
    //мы присоединились к классу MyServiceBean, и наши действия:
    // getSignature - получить сигнатуры (названия класса, методов и тип аргумента),
    // getArgs - получить все аргументы
  }

    // Advice - советы
    // 1. Before
    // 2. AfterReturning
    // 3. AfterThrowing
    // 4. After - сработает после, в любом случае!
    // 5. Around


  /**
   * Advice Before - срабатывает до получения метода
   * @param joinPoint
   */
  @Before("myServiceBeanMethodsPointcut()")
  private void beforeMyServiceBean(JoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    log.info("signature: {}", signature);
    log.info("Argument name: {}", joinPoint.getArgs()[0]);
  }

  /**
   * Очень интересный Advice, который работает вместо вызова.
   * JoinPoint уже другого типа, типа ProceedingJoinPoint
   * Теперь у нас есть действие joinPoint.proceed - запускается оригинальный метод (тот к которому мы присоединились).
   * Если есть Around, то только в нём теперь можно запустить оригинальный метод (методы)
   * @param joinPoint
   * @return - возвращает Object
   */
  @Around("myServiceBeanMethodsPointcut()")
  public Object aroundMyServiceBeanMethodsPointcut(ProceedingJoinPoint joinPoint) {
    try {
      Object proceed = joinPoint.proceed(); //всегда выбрасывает исключение в этом случает
      return proceed;
    } catch (Throwable e) {
      return "exception was thrown: [" + e.getMessage() + "]"; //которое надо обработать просто выведя в консоль
    }
  }


  /**
   * Advice AfterReturning - срабатывает после того как возвращаются значения
   * @param joinPoint
   * @param result - то что вернул тот метод, к которому мы присоединились
   */
  @AfterReturning(value = "myServiceBeanMethodsPointcut()", returning = "result")
  public void afterReturningMyServiceBean(JoinPoint joinPoint, Object result) {
    log.info("Result: {}", result);
  }

  /**
   * Advice AfterThrowing - срабатывает только тогда, когда исполнение метода закончилось выбросом exception
   * @param e
   */
  @AfterThrowing(value = "myServiceBeanMethodsPointcut()", throwing = "e")
  public void afterThrowingMyServiceBean(Throwable e) {
    log.error("Exception!!! {} - {}", e.getClass(), e.getMessage());
  }

}
