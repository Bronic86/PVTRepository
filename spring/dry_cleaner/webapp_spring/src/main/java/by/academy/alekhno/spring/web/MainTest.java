package by.academy.alekhno.spring.web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.academy.alekhno.spring.web.config.AppSecurityConfig;
import by.academy.alekhno.spring.web.config.Config;

public class MainTest {

	public static void main(String[] args) {

		AbstractApplicationContext contextS = new AnnotationConfigApplicationContext(
				AppSecurityConfig.class);
		System.out.println("Security OK");
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	}
}
