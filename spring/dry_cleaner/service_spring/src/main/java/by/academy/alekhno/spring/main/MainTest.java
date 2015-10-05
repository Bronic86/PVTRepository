package by.academy.alekhno.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;

import by.academy.alekhno.spring.config.AppServiceConfig;

public class MainTest {

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppServiceConfig.class);

		System.out.println(context.getBean("userService"));
		UserDetailsService userDetailsService = (UserDetailsService) context
				.getBean("userDetailsService");

		userDetailsService.loadUserByUsername("boris@mail.ru");

		System.out.println(userDetailsService.loadUserByUsername("test"));
	}

}
