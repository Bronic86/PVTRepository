package by.academy.alekhno.dao.interf;


import java.util.List;

import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomClotherDAO extends GenericDAO<ClotherPojo> {

	
	ClotherPojo getByModelId(int model_id) throws DaoHibernateException;

	List<ClotherPojo> getByTypeId(int type_id) throws DaoHibernateException;
}
