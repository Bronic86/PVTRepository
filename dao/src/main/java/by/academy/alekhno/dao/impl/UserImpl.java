package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Contact;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;

public class UserImpl extends AbstractDao<User> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO users (id, login, password, first_name, second_name, contact_id)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
//			return Bundle.getQueryResource("add.user");
		case DELETE:
			return "DELETE FROM users WHERE id=?";
//			return Bundle.getQueryResource("delete.user");
		case UPDATE:
			return "UPDATE users SET login=?, password=?, first_name=?, second_name=?,"
					+ " contact_id=? WHERE id=?";
//			return Bundle.getQueryResource("update.user");
		case GET_ALL:
			return "SELECT * FROM users";
//			return Bundle.getQueryResource("get.all.user");
		case GET_BY_ID:
			return "SELECT * FROM users WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.user");
		default:
			
		}
			
		return null;
	}

	@Override
	protected User getVO(ResultSet resultSet) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			user.setId(resultSet.getInt("id"));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setSecondName(resultSet.getString("second_name"));
			GenericDao<Contact> genericDao = new ContactImpl();
			Contact contact = new Contact();
			contact.setId(resultSet.getInt("type_id"));
			user.setContact(genericDao.getByID(contact));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, User user,
			SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, user.getId());
				preparedStatement.setString(2, user.getLogin());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getFirstName());
				preparedStatement.setString(5, user.getSecondName());
				preparedStatement.setInt(6, user.getContact().getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getFirstName());
				preparedStatement.setString(4, user.getSecondName());
				preparedStatement.setInt(5, user.getContact().getId());
				preparedStatement.setInt(6, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, user.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
	}

}
