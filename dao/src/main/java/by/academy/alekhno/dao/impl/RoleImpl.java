package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.Role;

public class RoleImpl extends AbstractDao<Role> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
//			return "INSERT INTO roles (id, name) VALUES (?, ?)";
			return Bundle.getQueryResource("query.add.role");
		case DELETE:
//			return "DELETE FROM roles WHERE id=?";
			return Bundle.getQueryResource("query.delete.role");
		case UPDATE:
//			return "UPDATE roles SET name=? WHERE id=?";
			return Bundle.getQueryResource("query.update.role");
		case GET_ALL:
//			return "SELECT * FROM roles";
			return Bundle.getQueryResource("query.get.all.role");
		case GET_BY_ID:
//			return "SELECT * FROM roles WHERE id=?";
			return Bundle.getQueryResource("query.get.by.id.role");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Role getVO(ResultSet resultSet) throws SqlException {
		// TODO Auto-generated method stub
		Role role = new Role();
		try {
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SqlException exc = new SqlException();
			exc.addMessage("Get role VO error.");
			throw exc;
		}
		return role;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Role role, SqlMethode sqlMethode) throws SqlException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, role.getId());
				preparedStatement.setString(2, role.getName());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Role setParam add error.");
				throw exc;
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Role setParam delete error.");
				throw exc;
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, role.getName());
				preparedStatement.setInt(2, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Role setParam update error.");
				throw exc;
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Role setParam get_by_id error.");
				throw exc;
			}
		default:
			
		}
	}

	
}
