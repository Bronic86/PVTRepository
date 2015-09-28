package by.academy.alekhno.spring.maintest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.config.AppDAOJPAConfig;
import by.academy.alekhno.database.dao.interf.UserPojoRepository;
import by.academy.alekhno.spring.pojo.UserPojo;
import by.academy.alekhno.spring.util.HibernateConfig;

@Transactional
public class MyTest {

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(HibernateConfig.class);

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppDAOJPAConfig.class);

		UserPojoRepository userPojoRepository = (UserPojoRepository) context
				.getBean("userPojoRepository");

		String login = "test@mail.ru";

		UserPojo user = new UserPojo();
		user.setLogin(login);
		user.setPassword("111");
		user.setFirstName("Test");
		user.setSecondName("Test");
		user.setTelephone(375L);

		UserPojo userPersistent = userPojoRepository.saveAndFlush(user);
		System.out.println(userPersistent);
		userPojoRepository.delete(userPersistent.getId());
	}

}
