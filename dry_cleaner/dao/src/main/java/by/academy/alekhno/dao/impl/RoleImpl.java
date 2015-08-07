package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Role;

public class RoleImpl extends AbstractDao<Role> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			return Bundle.getQueryResource("query.add.role");
		case DELETE:
			return Bundle.getQueryResource("query.delete.role");
		case UPDATE:
			return Bundle.getQueryResource("query.update.role");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.role");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.role");
		default:

		}

		return null;
	}

	@Override
	protected Role getVO(ResultSet resultSet) throws DaoException {
		// TODO Auto-generated method stub
		Role role = new Role();
		try {
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Get VO Role exception.");
		}
		return role;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Role role,
			SqlMethode sqlMethode) throws DaoException {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, role.getId());
				preparedStatement.setString(2, role.getName());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Role preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Role preparesStatement for DELETE exception.");
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, role.getName());
				preparedStatement.setInt(2, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Role preparesStatement for UPDATE exception.");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, role.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Role preparesStatement for GET_BY_ID exception.");
			}
		default:

		}
	}

}
