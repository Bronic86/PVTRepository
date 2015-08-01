package by.academy.alekhno.dao;

import java.util.List;

import by.academy.alekhno.vo.Role;

public interface RoleDao {

	Role getRole (Role role);
	List<Role> getRoles ();
	void addRole (Role role);
	void updateRole (Role role);
	void deleteRole (Role role);
	
	
}
