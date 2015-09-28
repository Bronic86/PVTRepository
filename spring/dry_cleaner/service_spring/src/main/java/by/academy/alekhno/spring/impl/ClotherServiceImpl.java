package by.academy.alekhno.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.dao.interf.ClotherPojoRepository;
import by.academy.alekhno.database.dao.interf.ModelPojoRepository;
import by.academy.alekhno.database.dao.interf.TypePojoRepository;
import by.academy.alekhno.spring.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

@Service("clotherService")
@Transactional
public class ClotherServiceImpl implements ClotherService {

	@Autowired
	private ClotherPojoRepository clotherRepository;

	@Autowired
	private ModelPojoRepository modelRepository;

	@Autowired
	private TypePojoRepository ctypeRepository;

	@Override
	public Clother getClotherById(int clother_id) {
		return null;
	}

	@Override
	public Clother getClotherByModelId(int model_id) {
		return null;
	}

	@Override
	public List<Type> getTypes() {
		return null;
	}

	@Override
	public List<Model> getModelsByTypeId(int type_id) {
		return null;
	}

	@Override
	public void addType(String name) {

	}

	@Override
	public void addModel(Model model) {

	}

	@Override
	public void addClother(Clother clother) {

	}

	@Override
	public void updateType(Type type) {

	}

	@Override
	public void updateModel(Model model) {

	}

	@Override
	public void updateClother(Clother clother) {

	}

	@Override
	public void deleteType(int id) {

	}

	@Override
	public void deleteModel(int id) {

	}

	@Override
	public void deleteClother(int id) {

	}

	@Override
	public List<Clother> getClothesByTypeId(int type_id) {
		return null;
	}

}
