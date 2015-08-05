package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ModelImpl extends AbstractDao<Model> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return Bundle.getQueryResource("query.add.model");
		case DELETE:
			return Bundle.getQueryResource("query.delete.model");
		case UPDATE:
			return Bundle.getQueryResource("query.update.model");
		case GET_ALL:
			return Bundle.getQueryResource("query.get.all.model");
		case GET_BY_ID:
			return Bundle.getQueryResource("query.get.by.id.model");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Model getVO(ResultSet resultSet) throws DaoException{
		// TODO Auto-generated method stub
		Model model = new Model();
		Type type = new Type();
		try {
			model.setId(resultSet.getInt("id"));
			model.setName(resultSet.getString("name"));
			type.setId(resultSet.getInt("type_id"));
			type.setName(resultSet.getString("type_name"));
			model.setType(type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Get VO Model exception");
		}
		return model;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Model model,
			SqlMethode sqlMethode) throws DaoException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setString(2, model.getName());
				Type type = model.getType();
				preparedStatement.setInt(3, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Model preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Model preparesStatement for DELETE exception.");
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, model.getName());
				Type type = model.getType();
				preparedStatement.setInt(2, type.getId());
				preparedStatement.setInt(3, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Model preparesStatement for UPDATE exception.");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Set Model preparesStatement for GET_BY_ID exception.");
			}
		default:
			
		}
	}

}
