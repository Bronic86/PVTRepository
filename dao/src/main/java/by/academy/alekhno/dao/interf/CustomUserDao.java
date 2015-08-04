package by.academy.alekhno.dao.interf;

import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.User;

public interface CustomUserDao {

	User getByLogin(User user) throws SqlException;
	
	
}
