package by.academy.alekhno.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.spring.dao.interf.CustomUserDAO;
import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	CustomUserDAO userDao;

	@Override
	public List<UserPojo> findAllUsers() {
		return userDao.getAll();
	}

	@Override
	public UserPojo findByLogin(String login) {
		return userDao.getByLogin(login);
	}

	@Override
	public UserPojo findByLoginAndPassword(String login, String password) {
		return userDao.getByLoginAndPassword(login, password);
	}

	@Override
	public void updateUser(UserPojo user) {
		userDao.update(user);
	}

	@Override
	public UserPojo getById(Integer id) {
		return userDao.getByID(id);
	}

	@Override
	public Set<RolePojo> getRoles(UserPojo user) {
		return userDao.getRolesByUser(user);
	}

	@Override
	public Integer saveUser(UserPojo user) {
		return userDao.add(user);

	}

	@Override
	public void deleteUserById(Integer id) {
		userDao.delete(id);
	}

	@Override
	public void addRole(UserPojo user, RolePojo role) {
		userDao.addRoleForUser(user, role);
	}

}
