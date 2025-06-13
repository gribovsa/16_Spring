package org.gribov.classwork03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
/**
 * Swagger — это инструмент для автоматической генерации документации для RESTful
 * API. Он позволяет вам описать структуру вашего API, а затем автоматически
 * создает красивую, интерактивную документацию, которую можно использовать для
 * проверки работы вашего API.
 */
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    /*
        Теперь, когда Swagger настроен, мы можем открыть Swagger UI, чтобы увидеть нашу
    документацию и проверить работу API. Для этого мы просто открываем веб-браузер
    и переходим по адресу http://localhost:8080/swagger-ui.html
     */
}
