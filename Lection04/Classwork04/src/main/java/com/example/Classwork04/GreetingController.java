package com.example.Classwork04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * В этом контроллере мы добавляем атрибут message в модель и возвращаем имя
 * нашего шаблона greeting. Когда пользователь переходит по адресу /greeting,
 * Thymeleaf берет шаблон greeting.html, заменяет th:text="${message}" на значение
 * message из модели и возвращает получившуюся страницу.
 */
@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("message", "Привет, Thymeleaf!");
        return "greeting";
    }
}
