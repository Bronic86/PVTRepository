package by.academy.alekhno.exception;


public class DaoException extends Exception {

	private String message = "";
	
	public DaoException(){
	}
	
	public DaoException(String message) {
		this.message = this.message + "\n" + message;
	}

	public void addMessage (String message) {
		this.message = this.message + "\n" + message;
	}

	public String getMessage () {
		return message;
	}
}
