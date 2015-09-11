package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomModelDAO extends GenericDAO<ModelPojo> {

	ModelPojo getByName(String name) throws DaoHibernateException;
	
	List<ModelPojo> getByTypeId(int type_id) throws DaoHibernateException;
}
