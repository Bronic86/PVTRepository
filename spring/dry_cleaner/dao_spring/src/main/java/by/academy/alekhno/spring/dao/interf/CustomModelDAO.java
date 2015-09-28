package by.academy.alekhno.spring.dao.interf;

import java.util.List;

import by.academy.alekhno.spring.pojo.ModelPojo;

public interface CustomModelDAO extends GenericDAO<ModelPojo, Integer> {

	ModelPojo getByName(String name);

	List<ModelPojo> getByTypeId(int type_id);
}
