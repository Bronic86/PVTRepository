package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Model;

public interface CustomModelDao extends GenericDao<Model>{

	int getIdByFields(Model model) throws DaoException;

	List<Model> getByTypeId(int type_id) throws DaoException;
}
