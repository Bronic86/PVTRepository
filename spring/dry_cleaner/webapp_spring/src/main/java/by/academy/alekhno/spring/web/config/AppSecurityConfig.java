package by.academy.alekhno.spring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/registration", "/authorization", "/", "/price_list").permitAll()
				// .anyRequest().permitAll()
				.antMatchers("/price_list/**").access("hasRole('ROLE_USER')")
				.antMatchers("/authorized**").access("isAuthenticated()").and();

		http.formLogin().loginPage("/authorization").loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/authorization/error").usernameParameter("j_username")
				.passwordParameter("j_password").permitAll();

		http.logout().permitAll().logoutUrl("/j_spring_security_logout")
				.logoutSuccessUrl("/authorization/logout").invalidateHttpSession(true);

		http.authorizeRequests().and().formLogin().defaultSuccessUrl("/authorized");

		http.exceptionHandling().accessDeniedPage("/403");

	}
}
