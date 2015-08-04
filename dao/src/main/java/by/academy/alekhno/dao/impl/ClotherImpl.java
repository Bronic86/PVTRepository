package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.enums.SqlMethodeEnum;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;

public class ClotherImpl extends AbstractDao<Clother> {

	@Override
	protected String getSql(SqlMethodeEnum sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return Bundle.getQueryResource("query.add.clother");
		case DELETE:
			return Bundle.getQueryResource("query.delete.clother");
		case UPDATE:
			return Bundle.getQueryResource("query.update.clother");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.clother");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.clother");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Clother getVO(ResultSet resultSet) throws SqlException {
		// TODO Auto-generated method stub
		Clother clother = new Clother();
		try {
			clother.setId(resultSet.getInt("id"));
			//May be it's bad realization
			GenericDao<Model> genericDao = new ModelImpl();
			Model model = new Model();
			model.setId(resultSet.getInt("model_id"));
			clother.setModel(genericDao.getByID(model));
			
			clother.setPrice(resultSet.getDouble("price"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SqlException exc = new SqlException();
			exc.addMessage("Get clother VO error.");
			throw exc;
		}
		return clother;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Clother clother,
			SqlMethodeEnum sqlMethode) throws SqlException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				
				preparedStatement.setInt(1, clother.getModel().getId());
				preparedStatement.setDouble(2, clother.getPrice());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Clother setParam add error.");
				throw exc;
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Clother setParam delete error.");
				throw exc;
			}
		case UPDATE:
			try {
				preparedStatement.setInt(1, clother.getModel().getId());
				preparedStatement.setDouble(2, clother.getPrice());
				preparedStatement.setInt(3, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Clother setParam update error.");
				throw exc;
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();SqlException exc = new SqlException();
				exc.addMessage("Clother setParam get_by_id error.");
				throw exc;
			}
		default:
			
		}
	}

}
