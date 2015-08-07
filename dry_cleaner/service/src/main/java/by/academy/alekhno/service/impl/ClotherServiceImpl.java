package by.academy.alekhno.service.impl;

import java.util.List;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.ModelImpl;
import by.academy.alekhno.dao.impl.TypeImpl;
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.CustomModelDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ClotherServiceImpl implements ClotherService {

	public List<Type> getTypes() throws DaoException {
		// TODO Auto-generated method stub
		CustomTypeDao daoType = new TypeImpl();
		List<Type> types = daoType.getAll();
		return types;
	}

	public List<Model> getModelsByTypeId(int type_id) throws DaoException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		List<Model> models = daoModel.getByTypeId(type_id);
		return models;
	}

	public List<Clother> getClothesByModelId(int model_id) throws DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		List<Clother> clothes = daoClother.getByModelId(model_id);
		return clothes;
	}

	public void addType(String name) throws DaoException, ServiceException {
		// TODO Auto-generated method stub
		CustomTypeDao daoType = new TypeImpl();
		Type type = new Type();
		type.setName(name);
		if (!existName(name)) {
			daoType.add(type);
		} else{
			throw new ServiceException("Type exist.");
		}
	}

	private boolean existName(String name) throws DaoException {
		// TODO Auto-generated method stub
		CustomTypeDao daoType = new TypeImpl();
		Type type = daoType.getByName(name);
		return type != null;
	}

	public void addModel(Model model) throws DaoException, ServiceException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		if (!existModel(model)) {
			daoModel.add(model);
		} else{
			throw new ServiceException("Type exist.");
		}
	}

	private boolean existModel(Model model) throws DaoException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		int id = daoModel.getIdByFields(model);
		return id != 0;
	}

	public void addClother(Clother clother) throws ServiceException, DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		if (!existClother(clother)) {
			daoClother.add(clother);
		} else{
			throw new ServiceException("Clother exist.");
		}
	}

	private boolean existClother(Clother clother) throws DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		int id = daoClother.getIdByFields(clother);
		return id != 0;
	}

	public void updateType(Type type) throws DaoException {
		// TODO Auto-generated method stub
		CustomTypeDao daoType = new TypeImpl();
		daoType.update(type);
	}

	public void updateModel(Model model) throws DaoException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		daoModel.update(model);
	}

	public void updateClother(Clother clother) throws DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		daoClother.update(clother);
	}

	public void deleteType(int type_id) throws DaoException, ServiceException {
		// TODO Auto-generated method stub
		CustomTypeDao daoType = new TypeImpl();
		CustomModelDao daoModel = new ModelImpl();
		Type type = new Type();
		type.setId(type_id);
		List<Model> models = daoModel.getByTypeId(type_id);
		if(models.isEmpty()){
			daoType.delete(type);
		} else {
			throw new ServiceException("Can't delete type.");
		}
	}

	public void deleteModel(int model_id) throws DaoException, ServiceException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		Model model = new Model();
		model.setId(model_id);
		CustomClotherDao daoClother = new ClotherImpl();
		List<Clother> clothes =  daoClother.getByModelId(model_id);
		if(clothes.isEmpty()){
			daoModel.delete(model);
		} else{
			throw new ServiceException("Can't delete model.");
		}
	}

	public void deleteClother(int id) throws DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		Clother clother = new Clother();
		clother.setId(id);
		daoClother.delete(clother);
	}

}
