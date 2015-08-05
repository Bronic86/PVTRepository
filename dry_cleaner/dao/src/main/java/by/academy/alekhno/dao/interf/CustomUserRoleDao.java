package by.academy.alekhno.dao.interf;

import java.util.List;



import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.UserRole;

public interface CustomUserRoleDao {

	List<UserRole> getByIdUser(int user_id) throws DaoException;
	
	
}
