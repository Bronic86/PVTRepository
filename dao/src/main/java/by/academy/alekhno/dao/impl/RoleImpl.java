package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Role;

public class RoleImpl extends AbstractDao<Role> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO roles (id, name) VALUES (?, ?)";
//			return Bundle.getQueryResource("add.role");
		case DELETE:
			return "DELETE FROM roles WHERE id=?";
//			return Bundle.getQueryResource("delete.role");
		case UPDATE:
			return "UPDATE roles SET name=? WHERE id=?";
//			return Bundle.getQueryResource("update.role");
		case GET_ALL:
			return "SELECT * FROM roles";
//			return Bundle.getQueryResource("get.all.role");
		case GET_BY_ID:
			return "SELECT * FROM roles WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.role");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Role getVO(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Role role = new Role();
		try {
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Role role, SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, role.getId());
				preparedStatement.setString(2, role.getName());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, role.getName());
				preparedStatement.setInt(2, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
	}

	
}
