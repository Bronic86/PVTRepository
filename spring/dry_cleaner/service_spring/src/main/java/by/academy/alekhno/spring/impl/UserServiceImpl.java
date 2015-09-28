package by.academy.alekhno.spring.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.dao.interf.OrderPojoRepository;
import by.academy.alekhno.database.dao.interf.RolePojoRepository;
import by.academy.alekhno.database.dao.interf.UserPojoRepository;
import by.academy.alekhno.spring.converter.ConverterPojoToVO;
import by.academy.alekhno.spring.converter.ConverterVOToPojo;
import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserPojoRepository userRepository;

	@Autowired
	private RolePojoRepository roleRepository;

	@Autowired
	private OrderPojoRepository orderRepository;

	@Override
	public User authorization(String login, String password) {
		password = modifyPassword(password);
		UserPojo userPojo = userRepository.getByLoginAndPassword(login, password);
		User user = ConverterPojoToVO.getUser(userPojo);
		return user;
	}

	private String modifyPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	@Override
	public User registration(User user) {
		UserPojo userPojo = ConverterVOToPojo.getUser(user);
		if (!loginExist(userPojo.getLogin())) {
			RolePojo rolePojo = roleRepository.getByName("user");
			userPojo.setRoles(new HashSet<RolePojo>((Collection<? extends RolePojo>) rolePojo));
			UserPojo userSaving = userRepository.saveAndFlush(userPojo);
			return ConverterPojoToVO.getUser(userSaving);
		} else {
			return null;
		}
	}

	private boolean loginExist(String login) {
		UserPojo userPojo = userRepository.getByLogin(login);
		return userPojo != null;
	}

	@Override
	public Set<Role> getRoleByUserId(int user_id) {
		UserPojo userPojo = userRepository.getOne(user_id);
		Set<Role> roles = new HashSet<Role>();
		for (RolePojo rolePojo : userPojo.getRoles()) {
			roles.add(ConverterPojoToVO.getRole(rolePojo));
		}
		return roles;
	}

	@Override
	public User getUserByLogin(String login) {
		UserPojo userPojo = userRepository.getByLogin(login);
		return ConverterPojoToVO.getUser(userPojo);
	}

	@Override
	public void addRoleToUser(User user, Role role) {
		UserPojo persistentUser = userRepository.findOne(user.getId());
		Set<RolePojo> rolesPojo = persistentUser.getRoles();
		rolesPojo.add(ConverterVOToPojo.getRole(role));
		persistentUser.setRoles(rolesPojo);
	}

	@Override
	public Set<User> getAll() {
		List<UserPojo> usersPojo = userRepository.findAll();
		Set<User> users = new HashSet<User>();
		for (UserPojo userPojo : usersPojo) {
			users.add(ConverterPojoToVO.getUser(userPojo));
		}
		return users;
	}

}
