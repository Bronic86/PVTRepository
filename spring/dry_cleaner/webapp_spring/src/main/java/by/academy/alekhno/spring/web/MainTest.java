package by.academy.alekhno.spring.web;

import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) {

		// AbstractApplicationContext contextS = new
		// AnnotationConfigApplicationContext(
		// AppSecurityConfig.class);
		// System.out.println("Security OK");
		// AbstractApplicationContext context = new
		// AnnotationConfigApplicationContext(Config.class);
		System.out.println(Pattern.matches("^[0-9]{1,5}$", "d"));
	}
}
