package by.academy.alekhno.spring.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.dao.interf.UserPojoRepository;
import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

	@Autowired
	private UserPojoRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserPojo user = userRepository.getByLogin(login);
		logger.info(user);
		// if (user == null) {
		// throw new UsernameNotFoundException("Username not found");
		// }
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (RolePojo role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
		}
		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getLogin(), user.getPassword(), roles);
		logger.info(userDetails.getUsername() + " - name\n" + userDetails.getPassword()
				+ " - name\n");
		return userDetails;
	}

}
