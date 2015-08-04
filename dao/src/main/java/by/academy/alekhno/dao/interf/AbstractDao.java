package by.academy.alekhno.dao.interf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.enums.SqlMethodeEnum;
import by.academy.alekhno.exception.SqlException;

public abstract class AbstractDao<T> implements GenericDao<T> {
	
	public List<T> getAll () throws SqlException{
		List<T> t = new ArrayList<T>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql(SqlMethodeEnum.GET_ALL));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				t.add(getVO(resultSet));
			}
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to get list.");
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement);
		}
		return t;
	}
	
	
	
	public void update(T t) throws SqlException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;		
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql(SqlMethodeEnum.UPDATE));
			setParam(preparedStatement, t, SqlMethodeEnum.UPDATE);
			preparedStatement.executeUpdate();			
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to update.");
			throw e;
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement);
		}		
	}



	public void delete(T t) throws SqlException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;		
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql(SqlMethodeEnum.DELETE));
			setParam(preparedStatement, t, SqlMethodeEnum.DELETE);
			preparedStatement.executeUpdate();			
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to delete.");
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement);
		}
	}



	public void add(T t) throws SqlException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql(SqlMethodeEnum.ADD));
			setParam(preparedStatement, t, SqlMethodeEnum.ADD);
			preparedStatement.executeUpdate();
			
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to add.");
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement);
		}
	}



	public T getByID(T t) throws SqlException {
		// TODO Auto-generated method stub
		T tFinding = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql(SqlMethodeEnum.GET_BY_ID));
			setParam(preparedStatement, t, SqlMethodeEnum.GET_BY_ID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				tFinding = getVO(resultSet);
			}			
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.addMessage("Error to get_by_id list.");
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement);
		}
		return tFinding;
	}



	protected void close(ResultSet resultSet, PreparedStatement preparedStatement) {
		
		if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	protected abstract String getSql(SqlMethodeEnum sqlMethode);
	
	protected abstract T getVO(ResultSet resultSet) throws SqlException;
	
	protected abstract void setParam(PreparedStatement preparedStatement, T t, SqlMethodeEnum sqlMethode) throws SqlException;
	
}
