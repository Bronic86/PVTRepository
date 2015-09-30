package by.academy.alekhno.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.academy.alekhno.spring.config.AppServiceConfig;

public class MainTest {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppServiceConfig.class);
		System.out.println(context.getBean("userService"));
	}

}
