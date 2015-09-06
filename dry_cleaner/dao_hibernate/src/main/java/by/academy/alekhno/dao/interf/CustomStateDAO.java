package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomStateDAO extends GenericDAO<State> {
	
	State getByState(String state) throws DaoHibernateException;
	
}
