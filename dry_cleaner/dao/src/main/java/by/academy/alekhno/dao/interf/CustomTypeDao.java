package by.academy.alekhno.dao.interf;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Type;

public interface CustomTypeDao extends GenericDao<Type> {

	Type getByName(String name) throws DaoException;
}
