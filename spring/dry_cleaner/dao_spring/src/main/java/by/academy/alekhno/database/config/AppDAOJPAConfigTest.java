package by.academy.alekhno.database.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import(HibernateConfigTest.class)
public class AppDAOJPAConfigTest {
	// Add bean

}
