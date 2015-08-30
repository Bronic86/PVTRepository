package by.academy.alekhno.exception;

public class DaoHibernateException extends Exception {
	private String message = "";
	private int id;
	

	public DaoHibernateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoHibernateException(String message, Throwable cause, int id) {
		super(message, cause);
		this.message = message;
		this.id = id;
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
