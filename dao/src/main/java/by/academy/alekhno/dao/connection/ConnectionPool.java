package by.academy.alekhno.dao.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import resources.bundle.Bundle;

public class Connection {
	private static final String BUNDLE_BASE_PATH = "base.path";
	private static final String BUNDLE_BASE_LOGIN = "base.login";
	private static final String BUNDLE_BASE_PASSWORD = "base.password";
	private static final String BUNDLE_BASE_DRIVER = "base.driver";
	
	private static Connection ourInstance;
    private Connection connection;

    public synchronized static Connection getInstance() {
        if (ourInstance == null) {
            ourInstance = new Connection();
            return ourInstance;
        }
        return ourInstance;
    }

    private Connection() {
        try {
            Class.forName(Bundle.getBaseResource(BUNDLE_BASE_DRIVER));
            
            connection = DriverManager.getConnection(
                    Bundle.getBaseResource(BUNDLE_BASE_PATH),
                    Bundle.getBaseResource(BUNDLE_BASE_LOGIN),
                    Bundle.getBaseResource(BUNDLE_BASE_PASSWORD));
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
