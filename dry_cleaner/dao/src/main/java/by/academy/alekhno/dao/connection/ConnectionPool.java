package by.academy.alekhno.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;



import org.apache.commons.dbcp.BasicDataSource;

import bundle.Bundle;

public class ConnectionPool {
	private static final String BUNDLE_BASE_PATH = "base.path";
	private static final String BUNDLE_BASE_LOGIN = "base.login";
	private static final String BUNDLE_BASE_PASSWORD = "base.password";
	private static final String BUNDLE_BASE_DRIVER = "base.driver";
	
	private static ConnectionPool ourInstance;
    private Connection connection;

    public synchronized static ConnectionPool getInstance() {
        if (ourInstance == null) {
            ourInstance = new ConnectionPool();
            return ourInstance;
        }
        return ourInstance;
    }

    private ConnectionPool() {
        try {        	
        	BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(Bundle.getBaseResource(BUNDLE_BASE_DRIVER));
            dataSource.setUrl(Bundle.getBaseResource(BUNDLE_BASE_PATH));      	
            dataSource.setUsername(Bundle.getBaseResource(BUNDLE_BASE_LOGIN));
            dataSource.setPassword(Bundle.getBaseResource(BUNDLE_BASE_PASSWORD));            
        	
            connection = dataSource.getConnection();
        	
        } catch (SQLException e){
        	e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
