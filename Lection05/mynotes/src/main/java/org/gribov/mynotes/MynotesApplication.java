package org.gribov.mynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication – это удобная аннотация, которая включает в себя
 * несколько других аннотаций Spring:
 * ● @Configuration делает этот класс источником определений бинов для
 * контекста приложения.
 * ● @EnableAutoConfiguration говорит Spring Boot начать добавлять бины в
 * зависимости от настроек класса, других бинов и различных настроек свойств.
 * ● @ComponentScan говорит Spring искать другие компоненты, конфигурации и
 * сервисы в пакете приложения, позволяя ему находить контроллеры.
 * Метод main() запускает наше приложение с помощью SpringApplication.run(),
 * который начинает выполнение Spring и, следовательно, веб-сервер Tomcat.
 */

@SpringBootApplication
public class MynotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynotesApplication.class, args);
	}

}
