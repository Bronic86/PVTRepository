package by.academy.alekhno.spring.interf;

import java.util.Set;

import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserService {

	User authorization(String login, String password);

	User registration(User user);

	Set<Role> getRoleByUserId(int user_id);

	User getUserByLogin(String login);

	void addRoleToUser(User user, Role role);

	Set<User> getAll();
}
