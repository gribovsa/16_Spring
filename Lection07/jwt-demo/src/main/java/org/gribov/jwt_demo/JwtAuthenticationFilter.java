package org.gribov.jwt_demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

/**
 * Добавляем фильтр JwtAuthenticationFilter в
 * конвейер безопасности. Этот фильтр будет обрабатывать запросы на эндпоинт
 * “/login” и создавать JWT для аутентифицированных пользователей.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /*
        В этом классе нам нужно переопределить метод attemptAuthentication для
    обработки учетных данных пользователя и метод successfulAuthentication для
    создания JWT после успешной аутентификации.
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        UserDetails principal = (UserDetails) authResult.getPrincipal();

        String token = Jwts.builder()
                .setSubject(principal.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() +
                        JwtProperties.EXPIRATION_TIME)) // Устанавливаем срок действия токена
                .signWith(SignatureAlgorithm.HS512, JwtProperties.SECRET) // Подписываем токен нашим серверным секретом
                .compact();

        response.addHeader(JwtProperties.HEADER_STRING,
                JwtProperties.TOKEN_PREFIX + token); // Добавляем токен в заголовок ответа
        /*
                JwtProperties - это просто класс, содержащий константы, связанные с JWT, такие
        как строка заголовка (“Authorization”), префикс токена (“Bearer”), секрет и время
        истечения токена.
         */
    }
}
