package by.academy.alekhno.exception;

public class DaoHibernateException extends Exception {
	private String message = "";
	private Throwable exClass;
	StackTraceElement[] stackTrace;
	

	public DaoHibernateException() {
		super();
	}

	public DaoHibernateException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public DaoHibernateException(Throwable cause) {
		super(cause);
		exClass = cause;
	}
	

	public DaoHibernateException(String message, Throwable cause,
			StackTraceElement[] stackTrace) {
		super(cause);
		this.message = message;
		exClass = cause;
		this.stackTrace = stackTrace;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getExClass() {
		return exClass;
	}

	public void setExClass(Throwable exClass) {
		this.exClass = exClass;
	}

	
	
}
