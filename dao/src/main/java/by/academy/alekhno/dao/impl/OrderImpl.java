package by.academy.alekhno.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;

public class OrderImpl extends AbstractDao<Order> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO orders (id, user_id, clother_id, quantity, ordering_day)"
					+ " VALUES (?, ?, ?, ?, ?)";
//			return Bundle.getQueryResource("add.order");
		case DELETE:
			return "DELETE FROM orders WHERE id=?";
//			return Bundle.getQueryResource("delete.order");
		case UPDATE:
			return "UPDATE orders SET user_id=?, clother_id=?, quantity=?, ordering_day=?,"
					+ " WHERE id=?";
//			return Bundle.getQueryResource("update.order");
		case GET_ALL:
			return "SELECT * FROM orders";
//			return Bundle.getQueryResource("get.all.order");
		case GET_BY_ID:
			return "SELECT * FROM orders WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.order");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Order getVO(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Order order = new Order();
		try {
			order.setId(resultSet.getInt("id"));
			
			GenericDao<User> genericDaoU = new UserImpl();
			User user = new User();
			user.setId(resultSet.getInt("user_id"));
			order.setUser(genericDaoU.getByID(user));
			
			GenericDao<Clother> genericDaoC = new ClotherImpl();
			Clother clother = new Clother();
			clother.setId(resultSet.getInt("clother_id"));
			order.setClother(genericDaoC.getByID(clother));
			
			order.setQuantity(resultSet.getInt("quantity"));
			order.setOrdering_day(resultSet.getDate("ordering_day"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Order order,
			SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, order.getId());
				preparedStatement.setInt(2, order.getUser().getId());
				preparedStatement.setInt(3, order.getClother().getId());
				preparedStatement.setInt(4, order.getQuantity());
				preparedStatement.setDate(5, (Date) order.getOrdering_day());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setInt(1, order.getUser().getId());
				preparedStatement.setInt(2, order.getClother().getId());
				preparedStatement.setInt(3, order.getQuantity());
				preparedStatement.setDate(4, (Date) order.getOrdering_day());
				preparedStatement.setInt(5, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, order.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
	}

}
