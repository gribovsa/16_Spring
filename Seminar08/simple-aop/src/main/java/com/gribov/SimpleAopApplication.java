package com.gribov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class SimpleAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAopApplication.class, args);
		Test test = new Test();
		test.testMethod();
	}
}
