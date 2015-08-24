package by.academy.alekhno.dao.interf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bundle.Bundle;
import by.academy.alekhno.exception.DaoException;

public abstract class AbstractDao<T> implements GenericDao<T> {
	private Logger logger = Logger.getLogger(AbstractDao.class.getName());
	private Connection connection;

	public List<T> getAll() throws DaoException {
		logger.debug("Start getAll");
		List<T> t = new ArrayList<T>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(getSql(SqlMethode.GET_ALL));
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				t.add(getVO(resultSet));
			}
		} catch (SQLException e) {
			logger.error("SQLException getAll", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		logger.debug("End getAll");
		return t;
	}

	public void update(T t) throws DaoException {
		logger.debug("Start update");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(getSql(SqlMethode.UPDATE));
			setParam(preparedStatement, t, SqlMethode.UPDATE);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("SQLException update", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(null, preparedStatement);
		}
		logger.debug("End update");
	}

	public void delete(T t) throws DaoException {
		logger.debug("Start delete");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(getSql(SqlMethode.DELETE));
			setParam(preparedStatement, t, SqlMethode.DELETE);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.debug("SQLException delete", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(null, preparedStatement);
		}
		logger.debug("End delete");
	}

	public void add(T t) throws DaoException {
		logger.debug("Start add");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(getSql(SqlMethode.ADD));
			setParam(preparedStatement, t, SqlMethode.ADD);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.debug("SQLException add", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		logger.debug("End add");
	}

	public T getByID(T t) throws DaoException {
		logger.debug("Start getByID");
		T tFinding = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(getSql(SqlMethode.GET_BY_ID));
			setParam(preparedStatement, t, SqlMethode.GET_BY_ID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				tFinding = getVO(resultSet);
			}
		} catch (SQLException e) {
			logger.debug("SQLException getByID", e);
			throw new DaoException(
					Bundle.getQueryResource("message.sql.exception"), 1);
		} finally {
			close(resultSet, preparedStatement);
		}
		logger.debug("End getByID");
		return tFinding;
	}

	protected void close(ResultSet resultSet,
			PreparedStatement preparedStatement) throws DaoException {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.debug("SQLException close resultSet", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
			logger.debug("Close resultSet");
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.debug("SQLException close preparedStatement", e);
				throw new DaoException(
						Bundle.getQueryResource("message.sql.exception"), 1);
			}
			logger.debug("Close preparedStatement");
		}
	}

	protected abstract String getSql(SqlMethode sqlMethode);

	protected abstract T getVO(ResultSet resultSet) throws DaoException;

	protected abstract void setParam(PreparedStatement preparedStatement, T t,
			SqlMethode sqlMethode) throws DaoException;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

}
