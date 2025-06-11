
## Создание проекта с помощью Maven (из консоли)

1. Сначала установите Maven, следуя инструкциям на официальном сайте:
https://maven.apache.org/install.html.

2. В консоли введём команду
~~~
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
~~~

3. Здесь com.mycompany.app — это пример groupId, а my-app — artifactId вашего проекта.


4. Чтобы собрать ваш проект, перейдите в директорию my-app и выполните следующую команду:
~~~
mvn package
~~~

5. Для запуска собранного приложения выполните команду:
~~~
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.Ap
~~~

6. Эта команда запустит класс App из пакета com.mycompany.app.
Если все сделано правильно, вы увидите следующий вывод:
~~~
Hello, World!
~~~

##Добавление зависимостей и плагинов

1. Maven создаст исполняемый JAR-файл с именем my-app-1.0-SNAPSHOT-jar-with-dependencies.jar,
который содержит все зависимости вашего проекта.

2. Для запуска этого JAR-файла выполните команду:
~~~
java -jartarget/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar
~~~

3. Теперь у вас есть полноценный Java- проект, созданный и собранный с помощью Maven.


## Создание проекта с помощью Gradle (из консоли)

1. Команда
~~~
gradle init --type java-application
~~~

2. Сборка проекта. Чтобы собрать проект, выполните следующую команду в корневом каталоге проекта:
~~~
./gradlew build
~~~

3. Запуск приложения. Чтобы запустить ваше приложение, выполните следующую команду:
~~~
./gradlew run
~~~



