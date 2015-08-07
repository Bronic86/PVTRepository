package by.academy.alekhno.dao.interf;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.User;

public interface CustomUserDao extends GenericDao<User> {

	User getByLogin(String login) throws DaoException;
	
	User getByLoginAndPassword(User user) throws DaoException;
	
}
