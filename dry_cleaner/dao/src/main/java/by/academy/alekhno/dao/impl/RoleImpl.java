package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomRole;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Role;

public class RoleImpl extends AbstractDao<Role> implements CustomRole {
	private Logger logger = Logger.getLogger(RoleImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.role");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.role");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.role");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.role");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
			return Bundle.getQueryResource("query.get.by.id.role");
		default:

		}

		return null;
	}

	@Override
	protected Role getVO(ResultSet resultSet) throws DaoException {
		logger.debug("Start getVO");
		Role role = new Role();
		try {
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			logger.error("SQLException getVO", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		}
		return role;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Role role,
			SqlMethode sqlMethode) throws DaoException {
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, role.getId());
				preparedStatement.setString(2, role.getName());
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, role.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, role.getName());
				preparedStatement.setInt(2, role.getId());
				logger.debug("SetParam choose UPDATE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose UPDATE", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case GET_ALL:
			logger.debug("SetParam choose GET_ALL");
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, role.getId());
				logger.debug("SetParam choose GET_BY_ID");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose GET_BY_ID", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		default:

		}
	}

}
