package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Type;

public class TypeImpl extends AbstractDao<Type> implements CustomTypeDao {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			return Bundle.getQueryResource("query.add.type");
		case DELETE:
			return Bundle.getQueryResource("query.delete.type");
		case UPDATE:
			return Bundle.getQueryResource("query.update.type");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.type");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.type");
		default:

		}

		return null;
	}

	@Override
	protected Type getVO(ResultSet resultSet) throws DaoException {
		// TODO Auto-generated method stub
		Type type = new Type();
		try {
			type.setId(resultSet.getInt("id"));
			type.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Get VO Type exception");
		}
		return type;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Type type,
			SqlMethode sqlMethode) throws DaoException {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, type.getId());
				preparedStatement.setString(2, type.getName());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Role preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Role preparesStatement for DELETE exception.");
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, type.getName());
				preparedStatement.setInt(2, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Role preparesStatement for UPDATE exception.");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Role preparesStatement for GET_BY_ID exception.");
			}
		default:

		}
	}

	public Type getByName(String name) throws DaoException {
		// TODO Auto-generated method stub
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
			throw new DaoException("GetByName Type exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return null;
	}

}
