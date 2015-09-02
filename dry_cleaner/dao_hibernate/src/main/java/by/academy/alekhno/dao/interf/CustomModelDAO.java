package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.Model;

public interface CustomModelDAO extends GenericDAO<Model> {

	Model getByName(String name);
	
	List<Model> getByTypeId(int type_id);
}
