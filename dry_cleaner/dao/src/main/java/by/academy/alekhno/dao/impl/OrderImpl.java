package by.academy.alekhno.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;

public class OrderImpl extends AbstractDao<Order> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return Bundle.getQueryResource("query.add.order");
		case DELETE:
			return Bundle.getQueryResource("query.delete.order");
		case UPDATE:
			return Bundle.getQueryResource("query.update.order");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.order");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.order");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Order getVO(ResultSet resultSet) throws DaoException {
		// TODO Auto-generated method stub
		Order order = new Order();
		try {
			order.setId(resultSet.getInt("id"));
			
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setSecondName(resultSet.getString("second_name"));
			user.setTelephone(resultSet.getLong("telephone"));
			order.setUser(user);
			
			Clother clother = new Clother();
			clother.setId(resultSet.getInt("id"));
			Model model = new Model();
			model.setId(resultSet.getInt("model_id"));
			model.setName(resultSet.getString("name"));
			Type type = new Type();
			type.setId(resultSet.getInt("type_id"));
			type.setName(resultSet.getString("type_name"));
			model.setType(type);
			clother.setPrice(resultSet.getDouble("price"));
			order.setClother(clother);
			
			order.setQuantity(resultSet.getInt("quantity"));
			order.setOrdering_day(resultSet.getDate("ordering_day"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Get VO Order exception");
		}
		return order;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Order order,
			SqlMethode sqlMethode) throws DaoException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, order.getId());
				User user = order.getUser();
				preparedStatement.setInt(2, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(3, clother.getId());
				preparedStatement.setInt(4, order.getQuantity());
				preparedStatement.setDate(5, (Date) order.getOrdering_day());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Order preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Order preparesStatement for DELETE exception.");
			}
		case UPDATE:
			try {
				User user = order.getUser();
				preparedStatement.setInt(1, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(2, clother.getId());
				preparedStatement.setInt(3, order.getQuantity());
				preparedStatement.setDate(4, (Date) order.getOrdering_day());
				preparedStatement.setInt(5, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Order preparesStatement for UPDATE exception.");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Order preparesStatement for GET_BY_ID exception.");
			}
		default:
			
		}
	}

}
