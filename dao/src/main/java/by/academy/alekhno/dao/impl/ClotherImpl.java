package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;

public class ClotherImpl extends AbstractDao<Clother> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO clothes (id, model_id, price) VALUES (?, ?, ?)";
//			return Bundle.getQueryResource("add.clother");
		case DELETE:
			return "DELETE FROM clothes WHERE id=?";
//			return Bundle.getQueryResource("delete.clother");
		case UPDATE:
			return "UPDATE clothes SET model_id=?, price=? WHERE id=?";
//			return Bundle.getQueryResource("update.clother");
		case GET_ALL:
			return "SELECT * FROM clothes";
//			return Bundle.getQueryResource("get.all.clother");
		case GET_BY_ID:
			return "SELECT * FROM clothes WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.clother");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Clother getVO(ResultSet resultSet) {
		// TODO Auto-generated method stub
		Clother clother = new Clother();
		try {
			clother.setId(resultSet.getInt("id"));
			
			GenericDao<Model> genericDao = new ModelImpl();
			Model model = new Model();
			model.setId(resultSet.getInt("model_id"));
			clother.setModel(genericDao.getByID(model));
			
			clother.setPrice(resultSet.getDouble("price"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clother;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Clother clother,
			SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, clother.getId());
				preparedStatement.setInt(2, clother.getModel().getId());
				preparedStatement.setDouble(3, clother.getPrice());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setInt(1, clother.getModel().getId());
				preparedStatement.setDouble(2, clother.getPrice());
				preparedStatement.setInt(3, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
	}

}
