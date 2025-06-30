
1. В зависимостях подключаем lombok транзитивно, 
чтобы она была доступна только в этом проекте,
и не наследовалась далее
```html
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
```

2. Это включает автокомплит - подсказки в yml файле
```html
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>
```

3. Для нашего стартера необходимы
```html
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

4. В само приложение, к которому подключаем созданный нами стартер добавим зависимость
где http-logger-starter название нашего стартера
```html
        <dependency>
            <groupId>ru.gb</groupId>
            <artifactId>http-logger-starter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```