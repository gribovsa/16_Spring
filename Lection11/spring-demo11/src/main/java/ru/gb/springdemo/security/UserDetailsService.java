package ru.gb.springdemo.security;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.gb.springdemo.aspect.Timer;
//import ru.gb.springdemo.model.Member;
//import ru.gb.springdemo.repository.UsersRepository;
//
//import java.util.List;
//
///**
// * Компонент - класс в котором обрабатываются данные о пользователе
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    @Autowired
//    UsersRepository usersRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    /**
//     * Метод позволяет получить username из WEB формы, и найти его в
//     * user репозитории и вернуть...
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //нахожу в моей базе пользователей, пользователя (membera)
//        Member member = usersRepository.findByName(username).
//                orElseThrow(() -> new UsernameNotFoundException("Не найден пользователь: " + username));
//        log.info(member.toString());
//        //создаю и возвращаю пользователя User  (org.springframework.security.core.userdetails)
//        return new User(member.getName(),"{bcrypt}" + passwordEncoder.encode(member.getPass()),
//                List.of(new SimpleGrantedAuthority(member.getRoles())));
//    }
//}
