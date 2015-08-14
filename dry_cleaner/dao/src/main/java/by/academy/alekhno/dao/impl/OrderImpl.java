package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bundle.Bundle;
import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;

public class OrderImpl extends AbstractDao<Order> implements CustomOrderDao {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
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
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, order.getId());
				User user = order.getUser();
				preparedStatement.setInt(2, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(3, clother.getId());
				preparedStatement.setInt(4, order.getQuantity());
				preparedStatement.setDate(5, new java.sql.Date(order.getOrdering_day().getTime()));
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Order preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Order preparesStatement for DELETE exception.");
			}
		case UPDATE:
			try {
				User user = order.getUser();
				preparedStatement.setInt(1, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(2, clother.getId());
				preparedStatement.setInt(3, order.getQuantity());
				preparedStatement.setDate(4, new java.sql.Date(order.getOrdering_day().getTime()));
				preparedStatement.setInt(5, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Order preparesStatement for UPDATE exception.");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(
						"Set Order preparesStatement for GET_BY_ID exception.");
			}
		default:

		}
	}

	public List<Order> getOrdersByUserId(int id) throws DaoException {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionPool.getInstance()
					.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.orders.by.user.id"));
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orders.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("GetOrdersByUserId exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return orders;
	}

	public List<Integer> getIdByFields(Order order) throws DaoException {
		// TODO Auto-generated method stub
		List<Integer> idList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.id.by.fields.order"));
			Clother clother = order.getClother();
			User user = order.getUser();
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, clother.getId());
			preparedStatement.setDate(3, new java.sql.Date(order.getOrdering_day().getTime()));
			preparedStatement.setInt(4, order.getQuantity());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				idList.add(resultSet.getInt("id"));
			}			
		} catch (SQLException e) {
			throw new DaoException("getIdByFields Clother exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return idList;
	}

	@Override
	public List<Order> getOrdersByClotherId(int id) throws DaoException {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = ConnectionPool.getInstance()
					.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.orders.by.clother.id"));
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orders.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("GetOrdersByClotherId exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return orders;
	}

}
