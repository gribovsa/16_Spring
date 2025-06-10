package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * В этом примере, когда мы делаем запрос на “/message”, контроллер вызывает
 * метод getMessage() из MyService. Если активный профиль - “development”, то будет
 * использоваться DevelopmentMyService, и мы получим “Development Service”. Если
 * активный профиль - “production”, то будет использоваться ProductionMyService, и мы
 * получим “Production Service”.
 */

@RestController
public class MyController {
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return myService.getMessage();
    }
}
