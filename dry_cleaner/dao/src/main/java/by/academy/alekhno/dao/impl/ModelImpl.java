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
import by.academy.alekhno.dao.interf.CustomModelDao;
import by.academy.alekhno.dao.interf.SqlMethode;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

public class ModelImpl extends AbstractDao<Model> implements CustomModelDao {
	private Logger logger = Logger.getLogger(ModelImpl.class.getName());

	@Override
	protected String getSql(SqlMethode sqlMethode) {
		switch (sqlMethode) {
		case ADD:
			logger.debug("GetSql choose ADD");
			return Bundle.getQueryResource("query.add.model");
		case DELETE:
			logger.debug("GetSql choose DELETE");
			return Bundle.getQueryResource("query.delete.model");
		case UPDATE:
			logger.debug("GetSql choose UPDATE");
			return Bundle.getQueryResource("query.update.model");
		case GET_ALL:
			logger.debug("GetSql choose GET_ALL");
			return Bundle.getQueryResource("query.get.all.model");
		case GET_BY_ID:
			logger.debug("GetSql choose GET_BY_ID");
			return Bundle.getQueryResource("query.get.by.id.model");
		default:

		}

		return null;
	}

	@Override
	protected Model getVO(ResultSet resultSet) throws DaoException {
		logger.debug("Start getVO");
		Model model = new Model();
		Type type = new Type();
		try {
			model.setId(resultSet.getInt("id"));
			model.setName(resultSet.getString("name"));
			type.setId(resultSet.getInt("type_id"));
			type.setName(resultSet.getString("type_name"));
			model.setType(type);
		} catch (SQLException e) {
			logger.error("SQLException getVO", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		}
		return model;
	}

	@Override
	protected void setParam(PreparedStatement preparedStatement, Model model,
			SqlMethode sqlMethode) throws DaoException {
		switch (sqlMethode) {
		case ADD:
			try {
				preparedStatement.setInt(1, model.getId());
				preparedStatement.setString(2, model.getName());
				Type type = model.getType();
				preparedStatement.setInt(3, type.getId());
				logger.debug("SetParam choose ADD");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose ADD", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case DELETE:
			try {
				preparedStatement.setInt(1, model.getId());
				logger.debug("SetParam choose DELETE");
				break;
			} catch (SQLException e) {
				logger.error("SQLException SetParam choose DELETE", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
		case UPDATE:
			try {
				preparedStatement.setString(1, model.getName());
				Type type = model.getType();
				preparedStatement.setInt(2, type.getId());
				preparedStatement.setInt(3, model.getId());
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
				preparedStatement.setInt(1, model.getId());
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

	public int getIdByFields(Model model) throws DaoException {
		logger.debug("Start getIdByFields");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.id.by.fields.model"), 1);
			preparedStatement.setString(1, model.getName());
			int type_id = model.getType().getId();
			preparedStatement.setInt(2, type_id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
		} catch (SQLException e) {
			logger.error("SQLException getIdByFields", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return 0;
	}

	public List<Model> getByTypeId(int type_id) throws DaoException {
		logger.debug("Start getByTypeId");
		List<Model> models = new ArrayList<Model>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = super.getConnection();
			preparedStatement = connection.prepareStatement(Bundle
					.getQueryResource("query.get.by.type_id.model"));
			preparedStatement.setInt(1, type_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				models.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException getByTypeId", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		return models;
	}
}
