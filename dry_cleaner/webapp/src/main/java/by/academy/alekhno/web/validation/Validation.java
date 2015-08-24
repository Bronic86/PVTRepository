package by.academy.alekhno.web.validation;

public interface Validation {

	boolean isLogin(String login);
	
	boolean isPassword(String password);
	
	boolean isFirstName(String firstName);
	
	boolean isSecondName(String secondName);
	
	boolean isTelephone(String telephone);

	boolean isNumber(String clother_id_param);
	
	
}
