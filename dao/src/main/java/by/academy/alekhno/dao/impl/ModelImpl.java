package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ModelImpl extends AbstractDao<Model> {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			return "INSERT INTO models (id, name, type_id) VALUES (?, ?, ?)";
//			return Bundle.getQueryResource("add.model");
		case DELETE:
			return "DELETE FROM models WHERE id=?";
//			return Bundle.getQueryResource("delete.model");
		case UPDATE:
			return "UPDATE models SET name=?, type_id=? WHERE id=?";
//			return Bundle.getQueryResource("update.model");
		case GET_ALL:
			return "SELECT * FROM models";
//			return Bundle.getQueryResource("get.all.model");
		case GET_BY_ID:
			return "SELECT * FROM models WHERE id=?";
//			return Bundle.getQueryResource("get.by.id.model");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Model getVO(ResultSet resultSet) {
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
			e.printStackTrace();
		}
		return model;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Model model,
			SqlMethode sqlMethode) {
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
				e.printStackTrace();
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, model.getName());
				preparedStatement.setInt(2, model.getType().getId());
				preparedStatement.setInt(3, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, model.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			
		}
	}

}
