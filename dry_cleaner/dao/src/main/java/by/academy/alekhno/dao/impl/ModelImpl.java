package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ModelImpl extends AbstractDao<Model> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
//			return "INSERT INTO models (id, name, type_id) VALUES (?, ?, ?)";
			return Bundle.getQueryResource("query.add.model");
		case DELETE:
//			return "DELETE FROM models WHERE id=?";
			return Bundle.getQueryResource("query.delete.model");
		case UPDATE:
//			return "UPDATE models SET name=?, type_id=? WHERE id=?";
			return Bundle.getQueryResource("query.update.model");
		case GET_ALL:
//			return "SELECT * FROM models";
			return Bundle.getQueryResource("query.get.all.model");
		case GET_BY_ID:
//			return "SELECT * FROM models WHERE id=?";
			return Bundle.getQueryResource("query.get.by.id.model");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Model getVO(ResultSet resultSet) throws SqlException {
		// TODO Auto-generated method stub
		Model model = new Model();
		try {
			model.setId(resultSet.getInt("id"));
			model.setName(resultSet.getString("name"));
			GenericDao<Type> genericDao = new TypeImpl();
			Type type = new Type();
			type.setId(resultSet.getInt("type_id"));
			model.setType(genericDao.getByID(type));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SqlException exc = new SqlException();
			exc.addMessage("Get model VO error.");
			throw exc;
		}
		return model;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Model model,
			SqlMethode sqlMethode) throws SqlException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setString(2, model.getName());
				preparedStatement.setInt(3, model.getType().getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Model setParam add error.");
				throw exc;
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Model setParam delete error.");
				throw exc;
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, model.getName());
				preparedStatement.setInt(2, model.getType().getId());
				preparedStatement.setInt(3, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Model setParam update error.");
				throw exc;
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Model setParam get_by_id error.");
				throw exc;
			}
		default:
			
		}
	}

}
