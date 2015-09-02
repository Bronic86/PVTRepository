package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.vo.Role;

public interface RoleDAO {

void update(Role role) ;

	List<Role> getAll() ;
	
	void delete(Role role);
	
	int add(Role role);
	
	Role getByID (Role role);
	
	void setSession(Session session);
	
	Role getByName(String name);
}
