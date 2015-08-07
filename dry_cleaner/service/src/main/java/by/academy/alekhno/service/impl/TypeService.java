package by.academy.alekhno.service;

import by.academy.alekhno.dao.impl.TypeImpl;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Type;

public class TypeService {

	public void add(String name) throws ServiceException {
		GenericDao<Type> daoType = new TypeImpl();
		Type type = new Type();
		type.setName(name);
		try {
			if (!typeExist(name)) {
				daoType.add(type);
			} else {
				throw new ServiceException("Add type error.");
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Add type error.\n" + e.getMessage());
		}
	}

	private boolean typeExist(String name) throws ServiceException {
		// TODO Auto-generated method stub
		Type type = getByName(name);
		int id = type.getId();
		return id != 0;
	}

	public Type getByName(String name) throws ServiceException {
		CustomTypeDao daoType = new TypeImpl();
		Type type = new Type();
		try {
			type = daoType.getByName(name);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("GetByName type error.\n"
					+ e.getMessage());
		}
		return type;
	}
	
	public void deleteById(int id) throws ServiceException{
		GenericDao<Type> daoType = new TypeImpl();
		Type type = new Type();
		type.setId(id);
		try {
			daoType.delete(type);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("deleteById type error.\n"
					+ e.getMessage());
		}
	}
	
	

}
