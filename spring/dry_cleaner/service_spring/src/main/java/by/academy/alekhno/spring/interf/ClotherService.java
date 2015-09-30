package by.academy.alekhno.spring.interf;

import java.util.List;

import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public interface ClotherService {

	Clother getClotherById(int clother_id);

	Clother getClotherByModelId(int model_id);

	List<Type> getTypes();

	List<Model> getModelsByTypeId(int type_id);

	void addType(String name);

	void addModel(Model model);

	void addClother(Clother clother);

	void updateType(Type type);

	void updateModel(Model model);

	void updateClother(Clother clother);

	void deleteType(int id);

	void deleteModel(int id);

	void deleteClother(int id);

	List<Clother> getClothesByTypeId(int type_id);
}
