package by.academy.alekhno.database.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import(HibernateConfig.class)
public class AppDAOJPAConfig {
	// Add bean

}
