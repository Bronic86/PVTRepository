package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import bundle.Bundle;
import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.UserDao;
import by.academy.alekhno.vo.User;

public class UserDaoImpl implements UserDao {
	private static final String BUNDLE_QUERY_GET_USER = "query.get.user";
	
	public User getUser(User user) {
		User userDB = new User();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		connection = ConnectionPool.getInstance().getConnection();
//		preparedStatement = connection.prepareStatement(Bundle.getQueryResource(BUNDLE_QUERY_GET_USER));
		preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login=?");
		preparedStatement.setString(1, user.getLogin());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			userDB.setId(resultSet.getInt("id"));
			userDB.setLogin(resultSet.getString("login"));
			userDB.setPassword(resultSet.getString("password"));
			userDB.setFirstName(resultSet.getString("first_name"));
			userDB.setSecondName(resultSet.getString("second_name"));
			userDB.setTelephone(resultSet.getLong("telephone"));
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close (resultSet, preparedStatement);
		}
		return userDB;
	}

	private void close(ResultSet resultSet, PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
