package by.academy.alekhno.dao.interf;

import java.util.Set;

import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomRoleDAO extends GenericDAO<Role>{

	Role getByName(String name) throws DaoHibernateException;

//	Set<Role> getByUser(User user);
}
