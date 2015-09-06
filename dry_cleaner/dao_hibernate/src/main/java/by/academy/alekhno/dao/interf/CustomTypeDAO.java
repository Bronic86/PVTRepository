package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomTypeDAO extends GenericDAO<Type> {

	Type getByName(String name) throws DaoHibernateException;
}
