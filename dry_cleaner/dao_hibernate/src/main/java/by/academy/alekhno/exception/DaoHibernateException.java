package by.academy.alekhno.exception;

public class DaoHibernateException extends Exception {
	private String message = "";
	

	public DaoHibernateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoHibernateException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
