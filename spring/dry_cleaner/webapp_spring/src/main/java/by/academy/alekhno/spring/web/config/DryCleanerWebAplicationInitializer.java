package by.academy.alekhno.spring.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import by.academy.alekhno.spring.config.AppServiceConfig;

public class DryCleanerWebAplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(Config.class);
		ctx.register(AppSecurityConfig.class);
		ctx.register(AppServiceConfig.class);

		servletContext.addListener(new ContextLoaderListener(ctx));

		ctx.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);

	}

}
