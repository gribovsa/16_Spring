## Результаты

1. Получить студента с индексом 1
    - http://localhost:8080/student/id/1

2. Получить список всех студентов
    - http://localhost:8080/student/all

3. Получить студента с именем Борис
    - http://localhost:8080/student/search?name=Борис

4. Получить студентов группы ТФ-1-98
    - http://localhost:8080/student/group/ТФ-1-98

5. Создать студента
    - Запрос пишем из Postman
    - POST http://localhost:8080/student/create
    - Тело запроса json строка:
    ~~~
    {"name":"Константин","groupName":"ТФ-9-98","id":10}
    ~~~

6. Удалить студента с индексом 6 
    - Запрос пишем из Postman
    - DELETE http://localhost:8080/student/delete/6