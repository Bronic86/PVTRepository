package by.academy.alekhno.dao.interf;


import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomRoleDAO extends GenericDAO<RolePojo>{

	RolePojo getByName(String name) throws DaoHibernateException;

//	Set<Role> getByUser(User user);
}
