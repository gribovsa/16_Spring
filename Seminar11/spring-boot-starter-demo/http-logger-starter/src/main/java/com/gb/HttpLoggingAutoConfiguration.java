package com.gb;

import com.gb.http.logging.LoggingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс АВТО конфигурации
 * Обязательно добавляем в ресурсы файл META-INF
 * org.springframework.boot.autoconfigure.AutoConfiguration.imports,
 * там прописываем название этого класса.
 * Теперь spring любого приложения, где в pom прописан наш стартер
 * будет сканировать этот класс компонент и регистрировать бины из него.
 */
@Configuration
@EnableConfigurationProperties(LoggingProperties.class) //прописываем класс настроек логирования
@ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")//аннотация вкл/выкл данный класс из application.yml
public class HttpLoggingAutoConfiguration {

  /**
   * Регистрируем бин, который доступен spring и вызывает наш логер (класс LoggingFilter)
   */
  @Bean
  LoggingFilter loggingFilter(LoggingProperties properties) {
    return new LoggingFilter(properties);
  }

}
