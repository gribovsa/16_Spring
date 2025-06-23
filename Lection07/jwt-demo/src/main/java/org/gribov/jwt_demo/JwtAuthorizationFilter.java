package org.gribov.jwt_demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

/**
 * Фильтр, который будет проверять JWT в заголовках запросов.
 * Мы можем использовать его для аутентификации пользователей в последующих запросах
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * В методе doFilterInternal нам нужно извлечь JWT из заголовка, проверить его и
     * установить аутентификацию для текущего контекста безопасности, если токен
     * действителен.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


    // Здесь будет код для проверки JWT

    }
}
