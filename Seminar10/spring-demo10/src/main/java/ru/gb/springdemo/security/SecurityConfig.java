package ru.gb.springdemo.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import ru.gb.springdemo.aspect.Timer;
//
///**
// * Класс где конфигурируется безопасность
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    /**
//     * Бин, метод - цепочка фильтров
//     * в котором описана последовательность авторизации пользователя
//     */
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(authorization -> authorization
//                        .requestMatchers("/ui/readers/**").hasAnyAuthority("reader", "admin") //чтобы зайти в ресурс "/ui/readers/**" требуется роль "reader", "admin"
//                        .requestMatchers("/ui/books/**").authenticated() //чтобы зайти в ресурс "/ui/books/**" требуется быть только аутентифицированным
//                        .requestMatchers("/ui/issues/**").hasAuthority("admin") //чтобы зайти в ресурс "/ui/issues/**" требуется роль "admin"
//                        .anyRequest().permitAll()) //в остальные ресурсы смогут заходить все
//                .formLogin(Customizer.withDefaults()) //использую стандартную форму spring для ввода логина и пароля
//                .build();
//    }
//}
