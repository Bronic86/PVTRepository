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
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ClotherImpl extends AbstractDao<Clother> implements
		CustomClotherDao {

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
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
	protected Clother getVO(ResultSet resultSet) throws DaoException {
		// TODO Auto-generated method stub
		Clother clother = new Clother();
		try {
			clother.setId(resultSet.getInt("id"));
			Model model = new Model();
			model.setId(resultSet.getInt("model_id"));
			model.setName(resultSet.getString("name"));
			Type type = new Type();
			type.setId(resultSet.getInt("type_id"));
			type.setName(resultSet.getString("type_name"));
			model.setType(type);
			clother.setModel(model);
			clother.setPrice(resultSet.getDouble("price"));
		} catch (SQLException e) {
			throw new DaoException("Get VO Clother exception");
		}
		return clother;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement,
			Clother clother, SqlMethode sqlMethode) throws DaoException {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			try {
				Model model = clother.getModel();
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setDouble(2, clother.getPrice());
				break;
			} catch (SQLException e) {
				throw new DaoException(
						"Set Clother preparesStatement for ADD exception.");
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				throw new DaoException(
						"Set Clother preparesStatement for DELETE exception. ");
			}
		case UPDATE:
			try {
				Model model = clother.getModel();
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setDouble(2, clother.getPrice());
				preparedStatement.setInt(3, clother.getId());
				break;
			} catch (SQLException e) {
				throw new DaoException(
						"Set Clother preparesStatement for UPDATE exception. ");
			}
		case GET_ALL:
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, clother.getId());
				break;
			} catch (SQLException e) {
				throw new DaoException(
						"Set Clother preparedStatement for GET_BY_ID exception.");
			}
		default:

		}
	}

	public int getIdByFields(Clother clother) throws DaoException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.id.by.fields.clother"));
			Model model = clother.getModel();
			preparedStatement.setInt(1, model.getId());
			preparedStatement.setDouble(2, clother.getPrice());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return resultSet.getInt("id");
			}			
		} catch (SQLException e) {
			throw new DaoException("getIdByFields Clother exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return 0;
	}

	public List<Clother> getByModelId(int model_id) throws DaoException {
		// TODO Auto-generated method stub
		List<Clother> clothes = new ArrayList<Clother>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.by.model_id.clother"));
			preparedStatement.setInt(1, model_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				clothes.add(getVO(resultSet));
			}			
		} catch (SQLException e) {
			throw new DaoException("getByModelId Clother exception");
		} finally {
			close(resultSet, preparedStatement);
		}
		return clothes;
	}

}
