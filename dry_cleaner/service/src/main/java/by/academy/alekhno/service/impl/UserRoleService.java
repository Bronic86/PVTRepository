package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.academy.alekhno.dao.impl.UserRoleImpl;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;

public class UserRoleService {

	public List<Role> getRoles(int user_id) throws ServiceException{
		List<Role> roles = new ArrayList<Role>();
		CustomUserRoleDao daoUserRole = new UserRoleImpl();
		try {
			List<UserRole> userRoles = daoUserRole.getByIdUser(user_id);
			for(UserRole userRole : userRoles){
				roles.add(userRole.getRole());
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("getRoles error.\n"
					+ e.getMessage());
		}		
		return roles;
	}
	
	public void addUserRole(int user_id, int role_id) throws ServiceException{
		GenericDao<UserRole> daoUserRole = new UserRoleImpl();
		UserRole userRole = new UserRole();
		Role role = new Role();
		role.setId(role_id);
		User user = new User();
		user.setId(user_id);
		userRole.setRole(role);
		userRole.setUser(user);
		try {
			daoUserRole.add(userRole);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("getRoles error.\n"
					+ e.getMessage());
		}
	}
}
