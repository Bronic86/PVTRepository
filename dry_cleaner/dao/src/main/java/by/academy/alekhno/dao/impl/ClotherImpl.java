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
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ClotherImpl extends AbstractDao<Clother> implements
		CustomClotherDao {
	private Logger logger = Logger.getLogger(ClotherImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		// TODO Auto-generated method stub
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.clother");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.clother");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.clother");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.clother");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
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
			logger.debug("Start getVO");
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
			logger.error("SQLException getVO", e);
			throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
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
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, clother.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case UPDATE:
			try {
				Model model = clother.getModel();
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setDouble(2, clother.getPrice());
				preparedStatement.setInt(3, clother.getId());
				logger.debug("SetParam choose UPDATE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose UPDATE", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case GET_ALL:
			logger.debug("SetParam choose GET_ALL");
			break;
		case GET_BY_ID:
			try {
				preparedStatement.setInt(1, clother.getId());
				logger.debug("SetParam choose GET_BY_ID");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose GET_BY_ID", e);
				throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
			}
		default:
			
		}
	}

	public int getIdByFields(Clother clother) throws DaoException {
		logger.debug("Start getIdByFields");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.id.by.fields.clother"));
			Model model = clother.getModel();
			preparedStatement.setInt(1, model.getId());
			preparedStatement.setDouble(2, clother.getPrice());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return resultSet.getInt("id");
			}			
		} catch (SQLException e) {
			logger.error("SQLException getIdByFields", e);
			throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return 0;
	}

	public List<Clother> getByModelId(int model_id) throws DaoException {
		logger.debug("Start getByModelId");
		List<Clother> clothes = new ArrayList<Clother>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle.getQueryResource("query.get.by.model_id.clother"));
			preparedStatement.setInt(1, model_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				clothes.add(getVO(resultSet));
			}			
		} catch (SQLException e) {
			logger.error("SQLException getByModelId", e);
			throw new DaoException(Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return clothes;
	}

}
