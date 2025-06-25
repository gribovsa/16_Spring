package ru.gb.springdemo.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * Компонент - класс, отвечает за кодирование паролей, сравнение (соответствие)
 */
@Component
public class MemberPasswordEncoder implements PasswordEncoder {
    /**
     * Кастомный метод шифрования сырого пароля (который введёт пользователь в WEB форму)
     * @param rawPassword сырой пароль, который введёт пользователь в WEB форму
     * @return возвращает зашифрованный пароль
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    /**
     * Метод сравнивает пароли
     * @param rawPassword сырой пароль, который введёт пользователь в WEB форму
     * @param encodedPassword зашифрованный хранимый пароль
     * @return возвращает результат сравнения (true или false)
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
