package by.academy.alekhno.service;

import by.academy.alekhno.dao.impl.ModelImpl;
import by.academy.alekhno.dao.interf.CustomModelDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Model;

public class ModelService {

	public void add(Model model) throws ServiceException {
		GenericDao<Model> daoModel = new ModelImpl();
		try {
			if (!modelExist(model)) {
				daoModel.add(model);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Add Model error.\n" + e.getMessage());
		}
	}

	private boolean modelExist(Model model) throws ServiceException {
		// TODO Auto-generated method stub
		CustomModelDao daoModel = new ModelImpl();
		int id;
		try {
			id = daoModel.getIdByFields(model);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Model Exist error.\n" + e.getMessage());
		}
		return id != 0;
	}

	public void delete(int id) throws ServiceException {
		GenericDao<Model> daoModel = new ModelImpl();
		Model model = new Model();
		try {
			daoModel.delete(model);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Model delete error.\n" + e.getMessage());
		}
	}
	
	

}
