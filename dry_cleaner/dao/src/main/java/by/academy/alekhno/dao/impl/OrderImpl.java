package by.academy.alekhno.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bundle.Bundle;
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
	private Logger logger = Logger.getLogger(OrderImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.order");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.order");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.order");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.order");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
			return Bundle.getQueryResource("query.get.by.id.order");
		default:

		}

		return null;
	}

	@Override
	protected Order getVO(ResultSet resultSet) throws DaoException {
		logger.debug("Start getVO");
		Order order = new Order();
		try {
			order.setId(resultSet.getInt("id"));

			User user = new User();
			user.setId(resultSet.getInt("user_id"));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(null);
			user.setFirstName(resultSet.getString("first_name"));
			user.setSecondName(resultSet.getString("second_name"));
			user.setTelephone(resultSet.getLong("telephone"));

			Type type = new Type();
			type.setId(resultSet.getInt("type_id"));
			type.setName(resultSet.getString("type_name"));
			
			Model model = new Model();
			model.setId(resultSet.getInt("model_id"));
			model.setName(resultSet.getString("model_name"));
			model.setType(type);
			
			Clother clother = new Clother();
			clother.setId(resultSet.getInt("clother_id"));
			clother.setModel(model);
			clother.setPrice(resultSet.getDouble("price"));
			
			order.setUser(user);
			order.setClother(clother);
			order.setQuantity(resultSet.getInt("quantity"));
			order.setOrdering_day(resultSet.getDate("ordering_day"));
		} catch (SQLException e) {
			logger.error("SQLException getVO", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		}
		return order;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Order order,
			SqlMethode sqlMethode) throws DaoException {
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, order.getId());
				User user = order.getUser();
				preparedStatement.setInt(2, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(3, clother.getId());
				preparedStatement.setInt(4, order.getQuantity());
				preparedStatement.setDate(5, new java.sql.Date(order
						.getOrdering_day().getTime()));
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, order.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case UPDATE:
			try {
				User user = order.getUser();
				preparedStatement.setInt(1, user.getId());
				Clother clother = order.getClother();
				preparedStatement.setInt(2, clother.getId());
				preparedStatement.setInt(3, order.getQuantity());
				preparedStatement.setDate(4, new java.sql.Date(order
						.getOrdering_day().getTime()));
				preparedStatement.setInt(5, order.getId());
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
				preparedStatement.setInt(1, order.getId());
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

	public List<Order> getOrdersByUserId(int user_id) throws DaoException {
		logger.debug("Start getOrdersByUserId");
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.orders.by.user.id"));
			preparedStatement.setInt(1, user_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orders.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException getOrdersByUserId", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return orders;
	}

	public List<Integer> getIdByFields(Order order) throws DaoException {
		logger.debug("Start getIdByFields");
		List<Integer> idList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.id.by.fields.order"));
			Clother clother = order.getClother();
			User user = order.getUser();
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, clother.getId());
			preparedStatement.setDate(3, new java.sql.Date(order
					.getOrdering_day().getTime()));
			preparedStatement.setInt(4, order.getQuantity());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				idList.add(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			logger.error("SQLException getIdByFields", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return idList;
	}

	@Override
	public List<Order> getOrdersByClotherId(int id) throws DaoException {
		logger.debug("Start getOrdersByClotherId");
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.orders.by.clother.id"));
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orders.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException getOrdersByClotherId", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return orders;
	}

}
