package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.apache.commons.codec.digest.DigestUtils;

import by.academy.alekhno.dao.impl.UserImpl;
import by.academy.alekhno.dao.impl.UserRoleImpl;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;

public class UserService {

	public void add(User user) throws ServiceException {
		// May be return created id
		GenericDao<User> daoUser = new UserImpl();
		try {
			if (loginExist(user.getLogin())) {
				daoUser.add(user);
			} else {
				throw new ServiceException("Login exist.");
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Add user error.\n" + e.getMessage());
		}
	}

	private boolean loginExist(String login) throws ServiceException {
		CustomUserDao daoUser = new UserImpl();
		User user = new User();
		try {
			user = daoUser.getByLogin(login);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("LoginExist user error.\n"
					+ e.getMessage());
		}
		return login.equals(user.getLogin());
	}

	public boolean authorization(String login, String password)
			throws ServiceException {
		CustomUserDao daoUser = new UserImpl();
		User user = new User();
		user.setLogin(login);
		password = modifyPassword(password);
		user.setPassword(password);
		try {
			daoUser.getByLoginAndPassword(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Authorization user error.\n"
					+ e.getMessage());
		}
		return login.equals(user.getLogin());
	}

	private String modifyPassword(String password) {
		// Override
		return DigestUtils.md5Hex(password);
	}

	public void update(User user) throws ServiceException {
		GenericDao<User> daoUser = new UserImpl();
		try {
			
			daoUser.update(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Add user error.\n" + e.getMessage());
		}
	}
	
	public List<Role> getRoleByUserId(int id) throws ServiceException{
		
		CustomUserRoleDao urDao = new UserRoleImpl();
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = null;
		try {
			userRoles = urDao.getByIdUser(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("GetRoleByUserId for user error.\n" + e.getMessage());
		}
		for(UserRole ur: userRoles){
			roles.add(ur.getRole());
		}
		return roles;
	}

}
