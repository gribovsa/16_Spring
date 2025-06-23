package ru.gb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.model.User;
import ru.gb.repository.UserRepository;

import java.util.List;

/**
 * Компонент - класс в котором обрабатываются данные о пользователе
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  /**
   * Метод позволяет получить username из WEB формы, и найти его в
   * user репозитории и вернуть...
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));


    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of(
      new SimpleGrantedAuthority(user.getRole())
    ));
  }

}
