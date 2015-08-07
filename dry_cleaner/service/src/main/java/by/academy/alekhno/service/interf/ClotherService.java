package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public interface ClotherService {

	List<Type> getTypes() throws DaoException;
	
	List<Model> getModelsByTypeId(int type_id) throws DaoException;
	
	List<Clother> getClothesByModelId(int model_id) throws DaoException;
	
	void addType(String name) throws DaoException, ServiceException;
	
	void addModel(Model model) throws DaoException, ServiceException;
	
	void addClother(Clother clother) throws ServiceException, DaoException;
	
	void updateType(Type type) throws DaoException;
	
	void updateModel(Model model) throws DaoException;
	
	void updateClother(Clother clother) throws DaoException;
	
	void deleteType(int id) throws DaoException, ServiceException;
	
	void deleteModel(int id) throws DaoException, ServiceException;
	
	void deleteClother(int id) throws DaoException;
}
