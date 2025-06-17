## Docker Desktop на Windows создание контейнера с  БД Postgres

1. Установить Docker Desktop на Windows
2. Из консоли (например PowerShell) 
   ~~~
   docker run --name mypostgres -e POSTGRES_DB=mynewdb -e POSTGRES_USER=gribov -e POSTGRES_PASSWORD=1234 -p 5433:5432 -d postgres
   ~~~
3. Контейнер создан
   - mypostgres - имя создаваемого контейнера
   - mynewdb - название БД
   - gribov - логин
   - 1234 - пароль
   - 5433:5432 - порт хоста, порт контейнера
   - postgres - название образа (из которого создаётся контейнер)

4. Для работы с БД из консоли
   ~~~
   docker exec -it mypostgres psql -U gribov mynewdb
   переходим
   mynewdb=#
   ~~~
   
5. Запросить список всех таблиц
   ~~~
   mynewdb=# \dt
   ~~~