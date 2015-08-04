package by.academy.alekhno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bundle.Bundle;
import by.academy.alekhno.dao.enums.SqlMethodeEnum;
import by.academy.alekhno.dao.interf.AbstractDao;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.Type;

public class TypeImpl extends AbstractDao<Type> {

	@Override
	protected String getSql(SqlMethodeEnum sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
//			return "INSERT INTO types (id, name) VALUES (?, ?)";
			return Bundle.getQueryResource("query.add.type");
		case DELETE:
//			return "DELETE FROM types WHERE id=?";
			return Bundle.getQueryResource("query.delete.type");
		case UPDATE:
//			return "UPDATE types SET name=? WHERE id=?";
			return Bundle.getQueryResource("query.update.type");
		case GET_ALL:
//			return "SELECT * FROM types";
			return Bundle.getQueryResource("query.get.all.type");
		case GET_BY_ID:
//			return "SELECT * FROM types WHERE id=?";
			return Bundle.getQueryResource("query.get.by.id.type");
		default:
			
		}
			
		return null;
	}

	@Override
	protected Type getVO(ResultSet resultSet) throws SqlException {
		// TODO Auto-generated method stub
		Type type = new Type();
		try {
			type.setId(resultSet.getInt("id"));
			type.setName(resultSet.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SqlException exc = new SqlException();
			exc.addMessage("Get type VO error.");
			throw exc;
		}
		return type;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Type type,
			SqlMethodeEnum sqlMethode) throws SqlException {
		// TODO Auto-generated method stub
		switch (sqlMethode){
		case ADD:
			try {
				preparedStatement.setInt(1, type.getId());
				preparedStatement.setString(2, type.getName());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Type setParam add error.");
				throw exc;
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Type setParam delete error.");
				throw exc;
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, type.getName());
				preparedStatement.setInt(2, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Type setParam update error.");
				throw exc;
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, type.getId());
				break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlException exc = new SqlException();
				exc.addMessage("Type setParam get_by_id error.");
				throw exc;
			}
		default:
			
		}
	}

	
}
