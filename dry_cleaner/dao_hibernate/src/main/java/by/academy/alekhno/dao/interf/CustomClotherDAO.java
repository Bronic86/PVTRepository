package by.academy.alekhno.dao.interf;


import by.academy.alekhno.database.pojo.Clother;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomClotherDAO extends GenericDAO<Clother> {

	
	Clother getByModelId(int model_id) throws DaoHibernateException;
}
