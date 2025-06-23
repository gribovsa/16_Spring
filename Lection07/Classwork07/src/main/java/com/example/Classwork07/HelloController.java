package com.example.Classwork07;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Теперь, когда у нас есть настройки безопасности, мы можем их протестировать. Для
 * этого мы можем создать простой контроллер, который возвращает какой-нибудь
 * текст, и попробовать получить доступ к нему.
 * Теперь, если вы попытаетесь открыть <a href="http://localhost:8080/hello">...</a> в браузере, вы
 * должны увидеть диалоговое окно, запрашивающее имя пользователя и пароль.
 * Если вы введете имя пользователя “user” и пароль “password”, вы должны увидеть
 * сообщение “Hello, world!”.
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
