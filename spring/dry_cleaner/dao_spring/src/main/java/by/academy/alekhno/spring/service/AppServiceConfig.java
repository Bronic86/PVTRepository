package by.academy.alekhno.spring.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import by.academy.alekhno.spring.util.AppDAOConfig;

@Configuration
@Import(AppDAOConfig.class)
public class AppServiceConfig {

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
