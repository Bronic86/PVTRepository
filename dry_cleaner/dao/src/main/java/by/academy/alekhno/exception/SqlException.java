package by.academy.alekhno.exception;

import java.sql.SQLException;

public class SqlException extends SQLException {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public void addMessage (String message) {
		this.message = this.message + "\n" + message;
	}

	public String getMessage () {
		return message;
	}
}
