package by.academy.alekhno.spring.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import by.academy.alekhno.spring.dao.impl.CustomClotherDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomModelDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomOrderDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomRoleDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomStateDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomTypeDAOImpl;
import by.academy.alekhno.spring.dao.impl.CustomUserDAOImpl;
import by.academy.alekhno.spring.dao.interf.CustomClotherDAO;
import by.academy.alekhno.spring.dao.interf.CustomModelDAO;
import by.academy.alekhno.spring.dao.interf.CustomOrderDAO;
import by.academy.alekhno.spring.dao.interf.CustomRoleDAO;
import by.academy.alekhno.spring.dao.interf.CustomStateDAO;
import by.academy.alekhno.spring.dao.interf.CustomTypeDAO;
import by.academy.alekhno.spring.dao.interf.CustomUserDAO;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("by.academy.alekhno.spring.aspect")
@Import(HibernateConfig.class)
public class AppDAOConfig {
	// Add bean

	@Bean
	public CustomUserDAO customUserDAO() {
		return new CustomUserDAOImpl();
	}

	@Bean
	public CustomTypeDAO customTypeDAO() {
		return new CustomTypeDAOImpl();
	}

	@Bean
	public CustomStateDAO customStateDAO() {
		return new CustomStateDAOImpl();
	}

	@Bean
	public CustomRoleDAO customRoleDAO() {
		return new CustomRoleDAOImpl();
	}

	@Bean
	public CustomOrderDAO customOrderDAO() {
		return new CustomOrderDAOImpl();
	}

	@Bean
	public CustomModelDAO customModelDAO() {
		return new CustomModelDAOImpl();
	}

	@Bean
	public CustomClotherDAO customClotherDAO() {
		return new CustomClotherDAOImpl();
	}

}
