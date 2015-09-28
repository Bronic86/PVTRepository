package by.academy.alekhno.spring.dao.interf;

import java.util.Set;

import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;

public interface CustomUserDAO extends GenericDAO<UserPojo, Integer> {

	UserPojo getByLogin(String login);

	UserPojo getByLoginAndPassword(String login, String password);

	void addRoleForUser(UserPojo user, RolePojo role);

	Set<RolePojo> getRolesByUser(UserPojo user);
}
