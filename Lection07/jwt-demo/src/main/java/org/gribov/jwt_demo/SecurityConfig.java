package org.gribov.jwt_demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Класс конфигурации безопасности.
 *
 * @Configuration и @EnableWebSecurity - это аннотации Spring, которые говорят,
 * что этот класс является конфигурационным классом и что он должен
 * использоваться для настройки безопасности нашего приложения.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Теперь мы можем добавить наши правила безопасности в класс SecurityConfig. Это
     * делается путем переопределения метода configure и указания настроек
     * безопасности с помощью HttpSecurity объекта:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //После создания JwtAuthorizationFilter нам нужно добавить его в нашу конфигурацию безопасности.
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().disable() // Отключаем защиту CSRF (Cross-Site Request Forgery), так как будем использовать JWT
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Позволяем всем пользователям доступ к эндпоинту "/login"
                .anyRequest().authenticated() // Все остальные эндпоинты требуют аутентификации
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // Добавляем наш фильтр аутентификации
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Не создаем сессию, так как будем использовать JWT
    }

}
