package ru.gb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

  /**
   * Бин, класс - цепочка фильтров
   * в котором описана последовательность авторизации пользователя
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(source -> {
      Map<String, Object> resourceAccess = source.getClaim("realm_access");
      List<String> roles = (List<String>) resourceAccess.get("roles");

      return roles.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    });

    return httpSecurity
      .authorizeHttpRequests(configurer -> configurer
        .requestMatchers("/api/resource/admin/**").hasAuthority("admin") //чтобы зайти в ресурс "/api/resource/admin/**" требуется роль "admin"
        .requestMatchers("/api/resource/user/**").hasAuthority("user")
        .requestMatchers("/api/resource/auth/**").authenticated() //чтобы пройти аутентификацию, но не быть авторизованным
        .requestMatchers("/api/resource").permitAll() //в этот ресурс смогут заходить все
        .anyRequest().denyAll() //вход в остальные ресурсы запрещён denyAll
      )
//      .formLogin(Customizer.withDefaults()) //стандартная форма spring для ввода логина и пароля

      // oauth2 - специальный протокол аутентификации, работает с JSON WEB Token (JWT)
      .oauth2ResourceServer(configurer -> configurer
        .jwt(jwtConfigurer -> jwtConfigurer
          .jwtAuthenticationConverter(converter))
      )
      .build();
  }


}
