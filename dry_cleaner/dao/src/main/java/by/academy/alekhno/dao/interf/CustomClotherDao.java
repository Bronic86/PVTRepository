package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Clother;

public interface CustomClotherDao extends GenericDao<Clother> {

	int getIdByFields(Clother clother) throws DaoException;

	List<Clother> getByModelId(int model_id) throws DaoException;
	
	
}
