package ru.gb;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

/**
 * В корне объявляют данный класс, в котором делают аннотацию @AutoConfigureWebTestClient
 * это сделано для того, чтобы контекст тестов поднимался один раз (единожды)
 * Все остальные тест классы наследуют этот класс.
 */
@ActiveProfiles("test") // активируем application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JUnitSpringBootBase.TestSecurityConfiguration.class)
@AutoConfigureWebTestClient
public abstract class JUnitSpringBootBase {

  @TestConfiguration
  static class TestSecurityConfiguration {

//    @Bean
//    SecurityFilterChain testSecurityFilterChain(HttpSecurity security) throws Exception {
//      return security.authorizedHttpRequests(registry -> registry
//        .anyRequest().permitAll()
//      ).build;
//    }

  }

}
