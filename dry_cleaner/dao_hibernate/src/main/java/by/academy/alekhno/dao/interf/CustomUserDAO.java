package by.academy.alekhno.dao.interf;

import java.util.Set;

import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomUserDAO extends GenericDAO<UserPojo>  {
	
	UserPojo getByLogin(String login) throws DaoHibernateException;
	
	UserPojo getByLoginAndPassword(UserPojo user) throws DaoHibernateException;
	
	void addRoleForUser(UserPojo user, RolePojo role) throws DaoHibernateException;

	Set<RolePojo> getRolesByUser(UserPojo user) throws DaoHibernateException;
}
