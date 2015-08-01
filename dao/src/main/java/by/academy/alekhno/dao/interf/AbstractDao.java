package by.academy.alekhno.dao.interf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.academy.alekhno.dao.connection.ConnectionPool;

public abstract class AbstractDao<T> implements GenericDao<T> {
	
	public List<T> getAll (){
		List<T> t = new ArrayList<T>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql("getAll"));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				t.add(getVO(resultSet);
			}
		} finally {
			close(resultSet, preparedStatement);
		}
		return t;
	}
	
	
	
	public void update(T t) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;		
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql("update"));
			setParam(preparedStatement, t);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement);
		}		
	}



	public void delete(T t) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;		
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql("delete"));
			setParam(preparedStatement, t);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement);
		}
	}



	public void add(T t) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;		
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql("delete"));
			setParam(preparedStatement, t);
			preparedStatement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement);
		}
	}



	public T getForID(T t) {
		// TODO Auto-generated method stub
		T tFinding = new T(); 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			Connection connection = ConnectionPool.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(getSql("delete"));
			setParam(preparedStatement, t);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				getVO(resultSet);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement);
		}
		return tFinding;
	}



	private void close(ResultSet resultSet, PreparedStatement preparedStatement) {
		
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
	
	abstract String getSql(String str);
	
	abstract T getVO(ResultSet resultSet);
	
	abstract void setParam(PreparedStatement preparedStatement, T t);
	
}
