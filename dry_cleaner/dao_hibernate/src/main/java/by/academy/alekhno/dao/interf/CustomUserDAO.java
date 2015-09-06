package by.academy.alekhno.dao.interf;

import java.util.Set;

import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomUserDAO extends GenericDAO<User>  {
	
	User getByLogin(String login) throws DaoHibernateException;
	
	User getByLoginAndPassword(User user) throws DaoHibernateException;
	
	void addRoleForUser(User user, Role role) throws DaoHibernateException;

	Set<Role> getRolesByUser(User user) throws DaoHibernateException;
}
