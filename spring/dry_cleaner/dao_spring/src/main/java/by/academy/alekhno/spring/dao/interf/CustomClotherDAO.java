package by.academy.alekhno.spring.dao.interf;

import java.util.List;

import by.academy.alekhno.spring.pojo.ClotherPojo;

public interface CustomClotherDAO extends GenericDAO<ClotherPojo, Integer> {

	ClotherPojo getByModelId(int model_id);

	List<ClotherPojo> getByTypeId(int type_id);
}
