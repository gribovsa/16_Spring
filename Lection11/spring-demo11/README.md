В этом примере (наше приложение http server - результат домашних заданий),
согласно лекции попытался подключить и настроить
Actuator
Micrometer

Файл pom.xml
```html
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
                <version>3.5.3</version>
            </dependency>
            <dependency>
                <groupId>io.micrometer</groupId>
                <artifactId>micrometer-core</artifactId>
                <version>1.15.1</version>
            </dependency>
            <dependency>
                <groupId>io.micrometer</groupId>
                <artifactId>micrometer-registry-prometheus</artifactId>
                <version>1.15.1</version>
            </dependency>
```

Файл application.yml
```yaml
management:
  info:
    env:
      enabled: true
  endpoints:
    web:
      exposure:
        include: "*"
  metrics:
    export:
      prometheus:
        enabled: true
info:
  app:
    name: "My Cool App"
    version: "1.0.0"
    description: "This app does something awesome!"
```
Информация теперь доступна по ссылкам 
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info
* http://localhost:8080/actuator/metrics
* http://localhost:8080/actuator/env

Метрики прометеуса (Prometheus - как драйвер собирающий метрики и предоставляющий их SCADA Grafana)
* http://localhost:8080/actuator/prometheus