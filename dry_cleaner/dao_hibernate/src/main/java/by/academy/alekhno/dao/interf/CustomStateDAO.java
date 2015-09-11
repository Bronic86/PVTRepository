package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomStateDAO extends GenericDAO<StatePojo> {
	
	StatePojo getByState(String state) throws DaoHibernateException;
	
}
