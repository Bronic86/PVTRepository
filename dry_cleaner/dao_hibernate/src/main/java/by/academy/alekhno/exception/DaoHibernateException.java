package by.academy.alekhno.exception;

public class DaoHibernateException extends Exception {
	private String message = "";
	private String exClass;
	

	public DaoHibernateException() {
		super();
	}

	public DaoHibernateException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public DaoHibernateException(Throwable cause) {
		super(cause);
		exClass = cause.getClass().getName();
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExClass() {
		return exClass;
	}

	public void setExClass(String exClass) {
		this.exClass = exClass;
	}

	
	
}
