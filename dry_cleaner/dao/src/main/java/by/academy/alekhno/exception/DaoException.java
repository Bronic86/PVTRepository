package by.academy.alekhno.exception;


public class DaoException extends Exception {

	private String message = "";
	private int id;
	
	public DaoException(){
	}
	
	public DaoException(String message, int id) {
		this.message = this.message + "\n" + message;
		this.id = id;
	}

	public void addMessage (String message, int id) {
		this.message = this.message + "\n" + message;
		this.id = id;
	}

	public String getMessage () {
		return message;
	}
	
	public int getId () {
		return id;
	}
}
