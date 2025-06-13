## HTTP запросы с помощью CURL

1. GET-запросы
   Для отправки GET-запроса в curl достаточно просто указать URL. Например, если вы
   хотите получить список всех задач, вы можете ввести следующую команду:
~~~
   curl http://localhost:8080/api/tasks
~~~

2. POST-запросы
   Для отправки POST-запросов и передачи данных в теле запроса в curl вы можете
   использовать флаг -d (или --data). Вам также потребуется указать заголовок
   Content-Type, чтобы сервер знал, в каком формате вы отправляете данные.
   Например, для создания новой задачи вы можете ввести следующую команду:
~~~
   curl -X POST -H "Content-Type: application/json" -d '{"name":"New
   Task","description":"New task description","completed":false}'
   http://localhost:8080/api/tasks
~~~

3. PUT-запросы
   PUT-запросы аналогичны POST-запросам, но они обычно используются для
   обновления существующих ресурсов. Например, для обновления задачи с
   идентификатором 1 вы можете ввести следующую команду:
~~~
   curl -X PUT -H "Content-Type: application/json" -d '{"name":"Updated
   Task","description":"Updated task description","completed":true}'
   http://localhost:8080/api/tasks/1
~~~

4. DELETE-запросы
   Наконец, для отправки DELETE-запроса в curl вы просто используете флаг -X DELETE
   (или --request DELETE). Например, для удаления задачи с идентификатором 1 вы
   можете ввести следующую команду:
~~~
   curl -X DELETE http://localhost:8080/api/tasks/1
~~~