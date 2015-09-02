package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;

public interface CustomUserDAO extends GenericDAO<User>  {
	
	User getByLogin(String login);
	
	User getByLoginAndPassword(User user);
	
	void addRoleForUser(User user, Role role);
}
