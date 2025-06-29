package ru.gb.springdemo.controllers;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
/**
 * В корне объявляют данный класс, в котором делают аннотацию @AutoConfigureWebTestClient
 * это сделано для того, чтобы контекст тестов поднимался один раз (единожды)
 * Все остальные тест классы наследуют этот класс.
 */
@ActiveProfiles("test") //активируем application-test.yml
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public abstract class JUnitSpringBootBase {
    @TestConfiguration
    static class TestSecurityConfiguration {
    }
}
