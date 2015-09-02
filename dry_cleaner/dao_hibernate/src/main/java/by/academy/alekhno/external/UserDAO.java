package by.academy.alekhno.external;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserDAO {

	List<User> getAll() ;
	
	void update(User user) ;
	
	void delete(User user);
	
	int add(User user);
	
	User getByID (User user);
	
	void setSession(Session session);
	
	User getByLogin(String login);
	
	User getByLoginAndPassword(User user);
	
	void addRoleForUser(User user, Role role);
	
	Set<Role> getRolesByUser(User user);
}
