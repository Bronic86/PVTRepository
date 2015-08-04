package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.enums.SqlMethodeEnum;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.User;

public class UserImpl extends AbstractDao<User> implements CustomUserDao {

	@Override
	protected String getSql(SqlMethodeEnum sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return Bundle.getQueryResource("query.add.user");
		case DELETE:
			return Bundle.getQueryResource("query.delete.user");
		case UPDATE:
			return Bundle.getQueryResource("query.update.user");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.user");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.user");
		default:
			
		}
			
		return null;
	}

	@Override
	protected User getVO(ResultSet resultSet) throws SqlException {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			user.setId(resultSet.getInt("id"));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setSecondName(resultSet.getString("second_name"));
			user.setTelephone(resultSet.getLong("telephone"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SqlException exc = new SqlException();
			exc.addMessage("Get user VO error.");
			throw exc;
		}
		return user;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, User user,
			SqlMethodeEnum sqlMethode) throws SqlException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, user.getId());
				preparedStatement.setString(2, user.getLogin());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getFirstName());
				preparedStatement.setString(5, user.getSecondName());
				preparedStatement.setLong(6, user.getTelephone());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("User setParam add error.");
				throw exc;
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("User setParam delete error.");
				throw exc;
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getFirstName());
				preparedStatement.setString(4, user.getSecondName());
				preparedStatement.setLong(5, user.getTelephone());
				preparedStatement.setInt(6, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("User setParam update error.");
				throw exc;
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("User setParam get_by_id error.");
				throw exc;
			}
		default:
			
		}
	}

	public User getByLogin(User user) throws SqlException {
		// TODO Auto-generated method stub
		User userFinding = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.user.by.login"));
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				userFinding = getVO(resultSet);
			}			
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to get_user_by_login.");
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement);
		}
		return userFinding;
	}

}
