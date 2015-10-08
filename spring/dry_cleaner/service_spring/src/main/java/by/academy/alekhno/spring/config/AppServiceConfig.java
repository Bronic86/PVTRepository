package by.academy.alekhno.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import by.academy.alekhno.database.config.AppDAOJPAConfig;

@Configuration
@EnableAspectJAutoProxy
@Import(AppDAOJPAConfig.class)
@ComponentScan("by.academy.alekhno.spring.impl")
public class AppServiceConfig {

	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}
}
