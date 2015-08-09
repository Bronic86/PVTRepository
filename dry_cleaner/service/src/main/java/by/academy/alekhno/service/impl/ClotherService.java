package by.academy.alekhno.service.impl;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Clother;

public class ClotherService {

	public void add(Clother clother) throws ServiceException, DaoException{
		GenericDao<Clother> daoClother = new ClotherImpl();
		if(!clotherExist(clother)){
			try {
				daoClother.add(clother);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				throw new ServiceException("add clother  error.\n" + e.getMessage());
			}
		} else {
			throw new ServiceException("Add clother exist.");
		}
	}

	private boolean clotherExist(Clother clother) throws DaoException {
		// TODO Auto-generated method stub
		CustomClotherDao daoClother = new ClotherImpl();
		int id = daoClother.getIdByFields(clother);
		return id != 0;
	}
}
