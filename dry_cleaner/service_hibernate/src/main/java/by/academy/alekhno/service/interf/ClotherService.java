package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.ClotherDAO;
import by.academy.alekhno.external.ModelDAO;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.TypeDAO;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public interface ClotherService {
	
	
	Clother getClotherById(int clother_id) throws ServiceException;
	
	Clother getClotherByModelId(int model_id) throws ServiceException;

	List<Type> getTypes() throws ServiceException;
	
	List<Model> getModelsByTypeId(int type_id) throws ServiceException;
	
	void addType(String name) throws ServiceException;
	
	void addModel(Model model) throws ServiceException;
	
	void addClother(Clother clother) throws ServiceException;
	
	void updateType(Type type) throws ServiceException;
	
	void updateModel(Model model) throws ServiceException;
	
	void updateClother(Clother clother) throws ServiceException;
	
	void deleteType(int id) throws ServiceException;
	
	void deleteModel(int id) throws ServiceException;
	
	void deleteClother(int id) throws ServiceException;
	
	void setDaoClother(ClotherDAO daoClother);
	
	void setDaoModel(ModelDAO daoModel);
	
	void setDaoType(TypeDAO daoType);
	
	void setDaoOrder(OrderDAO daoOrder);
}
