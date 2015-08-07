package by.academy.alekhno.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.dbcp.BasicDataSource;

import bundle.Bundle;
import by.academy.alekhno.exception.DaoException;

public class ConnectionPool {
	private static Lock lock = new ReentrantLock();
	private static final String BUNDLE_BASE_PATH = "base.path";
	private static final String BUNDLE_BASE_LOGIN = "base.login";
	private static final String BUNDLE_BASE_PASSWORD = "base.password";
	private static final String BUNDLE_BASE_DRIVER = "base.driver";
	
	private static ConnectionPool ourInstance;
    private volatile Connection connection;

    public static ConnectionPool getInstance() throws DaoException {
    	try{
	    	lock.lock();	
	    	
	        if (ourInstance == null) {
	            ourInstance = new ConnectionPool();
	            return ourInstance;
	        }
    	} finally {
    		lock.unlock();
    	}
        return ourInstance;
    }

    private ConnectionPool() throws DaoException {
        try {        	
        	BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(Bundle.getBaseResource(BUNDLE_BASE_DRIVER));
            dataSource.setUrl(Bundle.getBaseResource(BUNDLE_BASE_PATH));      	
            dataSource.setUsername(Bundle.getBaseResource(BUNDLE_BASE_LOGIN));
            dataSource.setPassword(Bundle.getBaseResource(BUNDLE_BASE_PASSWORD));            
        	
            connection = dataSource.getConnection();
        	
        } catch (SQLException e){
        	throw new DaoException("Open connection error");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
        	throw new DaoException("Close connection error");
        }
    }
	
}
