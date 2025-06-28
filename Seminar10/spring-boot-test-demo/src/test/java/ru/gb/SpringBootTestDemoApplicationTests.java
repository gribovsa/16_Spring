package ru.gb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Тестовый класс появляется по умолчанию при создании
 * проекта в spring boot (spring boot initializer)
 */
@SpringBootTest //аннотация поднимающая весь контекст целиком
class SpringBootTestDemoApplicationTests {

	/**
	 * Данный тест проверяет, что контекст (spring boot) успешно собран и запущен
	 */
	@Test
	void contextLoads() {
	}

}
