package com.gb.http.logging;

import com.gb.LoggingProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Класс работы с фильтр-компонентом spring, через который проходят ве http запросы и ответы
 */
@Slf4j
@RequiredArgsConstructor
public class LoggingFilter implements Filter {

  private final LoggingProperties properties; //инжекция нашего класса настроек логирования

  /**
   * Этот метод - фильтр компонент в spring через который проходят все запросы и ответы
   * можно выбрать запрос request, можно выбрать response
   * и есть chain - позволяет манипулировать цепочкой запросов и ответов (прервать её или продолжить)
   * Можно этот код вставлять в каждое наше приложение, а можно сделать стартер
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request; //получаем запрос
    logRequest(httpServletRequest); //отправляю в лог его
    chain.doFilter(request, response); //продолжить работу цепочку
    logResponse(httpServletRequest, (HttpServletResponse) response); //то же и с ответом
  }

  /**
   * Метод логирования запроса
   */
  private void logResponse(HttpServletRequest request, HttpServletResponse response) {
    doLog("response {}: status = {}", request.getServletPath(), response.getStatus());
  }

  /**
   * Метод логирования ответа
   */
  private void logRequest(HttpServletRequest request) throws IOException {
    doLog("request {}", request.getServletPath());
    //если логирование тела запроса мы включили = true
    if (properties.isLogBody()) {
      try (BufferedInputStream is = new BufferedInputStream(request.getInputStream())) {
        String body = new String(is.readAllBytes());
        doLog("request body: {}", body);
      }
    }
  }

  /**
   * Метод инициализации самого логера
   */
  private void doLog(String text, Object... params) {
    log.atLevel(properties.getLogLevel()).log(text, params);
  }

}
