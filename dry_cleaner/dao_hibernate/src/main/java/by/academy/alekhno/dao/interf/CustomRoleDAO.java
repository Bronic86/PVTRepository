package by.academy.alekhno.dao.interf;

import java.util.Set;

import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;

public interface CustomRoleDAO extends GenericDAO<Role>{

	Role getByName(String name);

//	Set<Role> getByUser(User user);
}
