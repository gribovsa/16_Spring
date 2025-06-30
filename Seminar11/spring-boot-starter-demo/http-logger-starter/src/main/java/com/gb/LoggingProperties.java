package com.gb;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс настроек логирования
 * в аннотации @ConfigurationProperties("http.logging") прописан ключ настроек
 */
@Data // нужны getter + setter
@ConfigurationProperties("http.logging") // настройки в application.yml нашего приложения (book-service)
public class LoggingProperties {

  /**
   * 1 изменяемый ключ.
   * Включить/выключить логирование тела запроса.
   */
  private boolean logBody = false;

  /**
   * 2 изменяемый ключ.
   * Уровень логирования.
   */
  private Level logLevel = Level.DEBUG;

}
