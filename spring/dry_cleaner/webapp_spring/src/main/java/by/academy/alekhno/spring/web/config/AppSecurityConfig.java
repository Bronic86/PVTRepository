package by.academy.alekhno.spring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
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
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/registration", "/authorization", "/").permitAll()
				// .anyRequest().permitAll()
				.antMatchers("/price_list/**").access("hasRole('ROLE_USER')").and();

		http.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/login?error").usernameParameter("j_username")
				.passwordParameter("j_password").permitAll();

		http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true);

		http.authorizeRequests().and().formLogin().defaultSuccessUrl("/");
		http.exceptionHandling().accessDeniedPage("/403");

		// http.authorizeRequests().antMatchers("/price_list/**").access("hasRole('ROLE_ADMIN')")
		// .antMatchers("/registration/**").access("hasRole('ROLE_SUPERADMIN')").and()
		// .formLogin().defaultSuccessUrl("/",
		// false).and().formLogin().loginPage("/login")
		// .usernameParameter("login").passwordParameter("password").and().csrf().and()
		// .exceptionHandling().accessDeniedPage("/");
		// http.authorizeRequests().antMatchers("/price_list/**").access("hasRole('ROLE_USER')")
		// .and().formLogin().loginPage("/authorization").failureUrl("/authorization/error")
		// .usernameParameter("username").passwordParameter("password").and().logout()
		// .logoutSuccessUrl("/authorization?logout").and().csrf()
		// .and().exceptionHandling().accessDeniedPage("/403");

		// http.csrf().disable().authorizeRequests().antMatchers("/resources/**",
		// "/**").permitAll()
		// .anyRequest().permitAll().and();

		// http.authorizeRequests().antMatchers("/price_list/**").access("hasRole('ROLE_USER')")
		// .formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
		// .failureUrl("/autherithation?error").usernameParameter("j_username")
		// .passwordParameter("j_password").permitAll().and().exceptionHandling()
		// .accessDeniedPage("/403");

		// http.logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/authorization?logout")
		// .invalidateHttpSession(true);
		//
		// http.authorizeRequests().and().formLogin().defaultSuccessUrl("/",
		// false);

		// http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/price_list/**")
		// .access("hasRole('ROLE_ADMIN')").and().formLogin().loginPage("/authorization")
		// .usernameParameter("j_username").passwordParameter("j_password").and().csrf().and()
		// .exceptionHandling().accessDeniedPage("/403");
	}
}
