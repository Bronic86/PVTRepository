package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomTypeDAO extends GenericDAO<TypePojo> {

	TypePojo getByName(String name) throws DaoHibernateException;
}
