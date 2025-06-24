package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import ru.gb.model.User;
import ru.gb.repository.UserRepository;
import ru.gb.security.SecurityConfiguration;

@SpringBootApplication
public class Application {

	// SecurityContext (SecurityContextHolder)
	// SecurityContextHolder = Map<String, SecurityContext>
	// Authorization <- [Principle (login), List<GrantedAuthority> roles]

	// UserDetails -
	// UserDetailsService
	// PasswordEncoder

	// SecurityFilterChain


	// Authorization: Basic base64(username+login)
	// Authorization: Bearer

	static long id = 1L;

	public static void main(String[] args) {
		UserRepository userRepository = SpringApplication.run(Application.class, args).getBean(UserRepository.class);

		saveUser(userRepository, "admin");
		saveUser(userRepository, "user");
		saveUser(userRepository, "auth");
		saveUser(userRepository, "simple");
	}

	private static void saveUser(UserRepository repository, String login) {
		User user = new User();
		user.setId(id++);
		user.setLogin(login);
		user.setPassword(login); //пароль совпадает с логином
		user.setRole(login); // роль совпадает с логином
		repository.save(user);
	}


	//остановился на  securityFilterChain 57:54
	//остановился на keycloak (контейнер на docker) - генерирует JWT токены 2:00:00

}
