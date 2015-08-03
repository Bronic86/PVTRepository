package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Contact;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;

public class UserRoleImpl extends AbstractDao<UserRole> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO users_role (id, user, role)"
					+ " VALUES (?, ?, ?)";
//			return Bundle.getQueryResource("add.user_role");
		case DELETE:
			return "DELETE FROM users_role WHERE id=?";
//			return Bundle.getQueryResource("delete.user_role");
		case UPDATE:
			return "UPDATE users_role SET user=?, role=? WHERE id=?";
//			return Bundle.getQueryResource("update.user_role");
		case GET_ALL:
			return "SELECT * FROM users_role";
//			return Bundle.getQueryResource("get.all.user_role");
		case GET_BY_ID:
			return "SELECT * FROM users_role WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.user_role");
		default:
			
		}
			
		return null;
	}

	@Override
	protected UserRole getVO(ResultSet resultSet) {
		// TODO Auto-generated method stub
		UserRole userRole = new UserRole();
		try {
			userRole.setId(resultSet.getInt("id"));
			
			GenericDao<Role> genericDao = new RoleImpl();
			Role role = new Role();
			role.setId(resultSet.getInt("role_id"));
			userRole.setRole(genericDao.getByID(role));
			
			GenericDao<User> genericDaoU = new UserImpl();
			User user = new User();
			user.setId(resultSet.getInt("user_id"));
			userRole.setUser(genericDaoU.getByID(user));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRole;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, UserRole userRole,
			SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, userRole.getId());
				preparedStatement.setInt(2, userRole.getUser().getId());
				preparedStatement.setInt(3, userRole.getRole().getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, userRole.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setInt(1, userRole.getUser().getId());
				preparedStatement.setInt(2, userRole.getRole().getId());
				preparedStatement.setInt(3, userRole.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, userRole.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
		
	}

}
