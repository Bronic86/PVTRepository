package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.User;

public class UserImpl extends AbstractDao<User> implements CustomUserDao {
	private Logger logger = Logger.getLogger(UserImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.user");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.user");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.user");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.user");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
			return Bundle.getQueryResource("query.get.by.id.user");
		default:

		}

		return null;
	}

	@Override
	protected User getVO(ResultSet resultSet) throws DaoException {
		logger.debug("Start getVO");
		User user = new User();
		try {
			user.setId(resultSet.getInt("id"));
			user.setLogin(resultSet.getString("login"));
			// May be return password=null
			user.setPassword(resultSet.getString("password"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setSecondName(resultSet.getString("second_name"));
			user.setTelephone(resultSet.getLong("telephone"));
		} catch (SQLException e) {
			logger.error("SQLException getVO", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		}
		logger.debug("End getVO");
		return user;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, User user,
			SqlMethode sqlMethode) throws DaoException {
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, user.getId());
				preparedStatement.setString(2, user.getLogin());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getFirstName());
				preparedStatement.setString(5, user.getSecondName());
				preparedStatement.setLong(6, user.getTelephone());
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, user.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getFirstName());
				preparedStatement.setString(4, user.getSecondName());
				preparedStatement.setLong(5, user.getTelephone());
				preparedStatement.setInt(6, user.getId());
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
				preparedStatement.setInt(1, user.getId());
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

	public User getByLogin(String login) throws DaoException {
		logger.debug("Start get user by login");
		User userFinding = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.by.login.user"));
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userFinding = getVO(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException getByLogin", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return userFinding;
	}

	public User getByLoginAndPassword(User user) throws DaoException {
		logger.debug("Start get user by login and password");
		User userFinding = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.by.login.and.password.user"));
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userFinding = getVO(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException getByLoginAndPassword", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return userFinding;
	}

}
