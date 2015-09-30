package by.academy.alekhno.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import by.academy.alekhno.database.config.AppDAOJPAConfig;

@Configuration
@EnableAspectJAutoProxy
@Import(AppDAOJPAConfig.class)
@ComponentScan("by.academy.alekhno.spring.impl")
public class AppServiceConfig {

}
