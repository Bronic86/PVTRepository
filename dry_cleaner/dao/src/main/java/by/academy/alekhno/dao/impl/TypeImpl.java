package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import bundle.Bundle;
import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Type;

public class TypeImpl extends AbstractDao<Type> implements CustomTypeDao {
	private Logger logger = Logger.getLogger(TypeImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.type");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.type");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.type");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.type");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
			return Bundle.getQueryResource("query.get.by.id.type");
		default:

		}

		return null;
	}

	@Override
	protected Type getVO(ResultSet resultSet) throws DaoException {
		logger.debug("Start getVO");
		Type type = new Type();
		try {
			type.setId(resultSet.getInt("id"));
			type.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			logger.error("SQLException getVO", e);
			throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
		}
		return type;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Type type,
			SqlMethode sqlMethode) throws DaoException {
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, type.getId());
				preparedStatement.setString(2, type.getName());
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, type.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, type.getName());
				preparedStatement.setInt(2, type.getId());
				logger.debug("SetParam choose UPDATE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose UPDATE", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
			}
		case GET_ALL:
			logger.debug("SetParam choose GET_ALL");
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, type.getId());
				logger.debug("SetParam choose GET_BY_ID");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose GET_BY_ID", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
			}
		default:

		}
	}

	public Type getByName(String name) throws DaoException {
		logger.debug("Start getByName");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.by.name.type"));
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return getVO(resultSet);
			}			
		} catch (SQLException e) {
			logger.error("SQLException getByName", e);
			throw new DaoException(Bundle.getQueryResource("message.sql.exception"));
		} finally {
			close(resultSet, preparedStatement);
		}
		return null;
	}

}
