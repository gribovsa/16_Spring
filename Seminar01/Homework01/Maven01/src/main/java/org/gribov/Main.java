package org.gribov;

import com.google.gson.Gson;

/**todo
     * Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код,
     * использующий эти зависимости.
     * Задание:
     * 1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
     * 2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
     * 3. Создайте класс Person с полями firstName, lastName и age.
     * 4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
     * 5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
 */
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Ivan", "Ivanov", 20);
        Person person2 = new Person("Petr", "Petrov", 26);
        Person person3 = new Person("Nikolay", "Nikolaev", 22);

        //commons-lang методы toString, equals и hashCode
        System.out.println(person1.equals(person2));
        System.out.println(person1.equals(person3));
        System.out.println(person1.hashCode());
        System.out.println(person1);

        //google.code.gson
        Gson gson = new Gson();

        String json1 = gson.toJson(person1); //сериализация
        System.out.println(json1);
        Person read1 = gson.fromJson(json1, Person.class); //десериализация
        System.out.println(read1.getFirstName());


        String json2 = gson.toJson(person2);
        System.out.println(json2);
        Person read2 = gson.fromJson(json2, Person.class);
        System.out.println(read2.getFirstName());


        String json3 = gson.toJson(person3);
        System.out.println(json3);
        Person read3 = gson.fromJson(json3, Person.class);
        System.out.println(read3.getFirstName());
    }
}
