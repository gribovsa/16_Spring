### Шаг 1: Настройка проекта
    Перейдите на сайт Spring Initializr и выберите следующие параметры:
        ● Project: Maven Project (если вы предпочитаете Gradle, можете выбрать Gradle
        Project)
        ● Language: Java
        ● Spring Boot: Выберите последнюю стабильную версию
        ● Project Metadata: Введите информацию о своем проекте. Например:
            – Group: com.example
            – Artifact: mynotes
            – Name: MyNotes
            – Description: A simple note management application
            – Package Name: com.example.mynotes
### Шаг 2: Выбор зависимостей
        Теперь нам нужно выбрать зависимости, которые нам понадобятся для нашего
        проекта. В случае с нашим приложением для управления заметками, нам
        понадобятся следующие зависимости:
        ● Spring Web: Для создания веб-приложения с использованием Spring MVC.
        ● Spring Data JPA: Для работы с базой данных через JPA.
        ● Thymeleaf: Для создания веб-страниц нашего приложения (это не
        обязательно, если мы планируем создать REST API).
        ● Spring Boot DevTools: Для автоматической перезагрузки приложения при
        изменении кода.
        ● PostgreSQL: Драйвер для нашей базы данных. Мы будем использовать
        postgres.