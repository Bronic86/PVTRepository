package by.academy.alekhno.spring.service;

import java.util.List;
import java.util.Set;

import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;

public interface UserService {

	Integer saveUser(UserPojo user);

	List<UserPojo> findAllUsers();

	UserPojo findByLogin(String login);

	UserPojo findByLoginAndPassword(String login, String password);

	void updateUser(UserPojo user);

	UserPojo getById(Integer id);

	Set<RolePojo> getRoles(UserPojo user);

	void deleteUserById(Integer id);

	void addRole(UserPojo user, RolePojo role);
}
