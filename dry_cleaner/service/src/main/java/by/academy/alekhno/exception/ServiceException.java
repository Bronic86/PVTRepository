package by.academy.alekhno.exception;

public class ServiceException extends Exception {
	private String message;
//	private String stackTrace;

	public ServiceException(String message) {
		setMessage(message);
	}
	
//	public ServiceException(String message, String stackTrace) {
//		setMessage(message);
//		setStackTrace(stackTrace);
//	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = this.message + message;
	}

//	public String getStackTrace() {
//		return stackTrace;
//	}
//
//	public void setStackTrace(String stackTrace) {
//		this.stackTrace = stackTrace;
//	}
	
	
	
}
