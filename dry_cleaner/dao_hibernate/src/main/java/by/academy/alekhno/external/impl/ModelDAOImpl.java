package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomModelDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.ModelDAO;
import by.academy.alekhno.vo.Model;

public class ModelDAOImpl implements ModelDAO {

	private CustomModelDAO dao = new by.academy.alekhno.dao.impl.ModelDAOImpl();
	private by.academy.alekhno.database.pojo.Model modelP;
	private List<by.academy.alekhno.database.pojo.Model> modelsP;

	@Override
	public List<Model> getAll() {
		List<Model> modelsVO = new ArrayList<>();
		modelsP = dao.getAll();
		for (by.academy.alekhno.database.pojo.Model modelP : modelsP) {
			modelsVO.add(ConverterPojoToVO.getModel(modelP));
		}
		return modelsVO;
	}

	@Override
	public void update(Model model) {
		modelP = ConverterVOToPojo.getModel(model);
		dao.update(modelP);
	}

	@Override
	public void delete(Model model) {
		modelP = ConverterVOToPojo.getModel(model);
		dao.delete(modelP);
	}

	@Override
	public int add(Model model) {
		modelP = ConverterVOToPojo.getModel(model);
		int id = dao.add(modelP);
		return id;
	}

	@Override
	public Model getByID(Model model) {
		modelP = ConverterVOToPojo.getModel(model);
		modelP = dao.getByID(modelP);
		model = ConverterPojoToVO.getModel(modelP);
		return model;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

	@Override
	public Model getByName(String name) {
		modelP = dao.getByName(name);
		Model model = ConverterPojoToVO.getModel(modelP);
		return model;
	}

	@Override
	public List<Model> getByTypeId(int type_id) {
		List<Model> modelsVO = new ArrayList<>();
		modelsP = dao.getByTypeId(type_id);
		for (by.academy.alekhno.database.pojo.Model modelP : modelsP) {
			modelsVO.add(ConverterPojoToVO.getModel(modelP));
		}
		return modelsVO;
	}

}
