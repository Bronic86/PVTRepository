package by.academy.alekhno.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import by.academy.alekhno.spring.config.AppServiceConfig;
import by.academy.alekhno.spring.interf.UserService;

public class MainTest {

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppServiceConfig.class);
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService);
		UserDetailsService userDetailsService = (UserDetailsService) context
				.getBean("userDetailsService");

		userDetailsService.loadUserByUsername("boris@mail.ru");

		System.out.println("\n\n" + userDetailsService.loadUserByUsername("test") + "\n\n");

		String password = "boris";
		String login = "boris@mail.ru";
		Md5PasswordEncoder passwordEncoder = (Md5PasswordEncoder) context
				.getBean("passwordEncoder");
		System.out.println(passwordEncoder.encodePassword(password, null) + "\n\n");

		System.out.println(userService.authorization(login, password));
	}

}
