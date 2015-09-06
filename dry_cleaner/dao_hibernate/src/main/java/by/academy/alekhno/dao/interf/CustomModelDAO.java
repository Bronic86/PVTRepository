package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.Model;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomModelDAO extends GenericDAO<Model> {

	Model getByName(String name) throws DaoHibernateException;
	
	List<Model> getByTypeId(int type_id) throws DaoHibernateException;
}
