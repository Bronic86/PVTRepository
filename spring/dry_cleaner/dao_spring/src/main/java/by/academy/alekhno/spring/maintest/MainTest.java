package by.academy.alekhno.spring.maintest;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;
import by.academy.alekhno.spring.service.AppServiceConfig;
import by.academy.alekhno.spring.service.UserService;

public class MainTest {

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppServiceConfig.class);

		// AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext();
		// context.scan("by.academy.alekhno.spring.util");
		// context.refresh();

		UserService service = (UserService) context.getBean("userService");

		String login = "test@mail.ru";

		UserPojo user = new UserPojo();
		user.setLogin(login);
		user.setPassword("111");
		user.setFirstName("Test");
		user.setSecondName("Test");
		user.setTelephone(375L);

		int id = service.saveUser(user);

		System.out.println("id " + id);
		List<UserPojo> users = service.findAllUsers();

		System.out.println(users);

		RolePojo role = new RolePojo();
		role.setId(2);

		service.addRole(user, role);

		System.out.println(service.findByLogin(login));

		System.out.println(service.findByLogin("boris@mail.ru"));
		service.deleteUserById(id);
	}

}
