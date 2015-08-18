package by.academy.alekhno.web.validation;

import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

	private static final String LOGIN_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	private static final String PASSWORD_REGEX = "^[\\w\\d]*$";
	private static final String FIRST_NAME_REGEX = "[A-Za-z0-9_а-яА-ЯёЁ]{1,30}";
	private static final String SECOND_NAME_REGEX = "[A-Za-z0-9_а-яА-ЯёЁ]{1,30}";
	private static final String TELEPHONE_REGEX = "[0-9]{12}";

	public boolean isLogin(String login) {
		// TODO Auto-generated method stub
		return Pattern.compile(LOGIN_REGEX).matcher(login).find();
	}

	public boolean isPassword(String password) {
		// TODO Auto-generated method stub
		return Pattern.compile(PASSWORD_REGEX).matcher(password).find();
	}

	public boolean isFirstName(String firstName) {
		// TODO Auto-generated method stub
		return Pattern.compile(FIRST_NAME_REGEX).matcher(firstName).find();
	}

	public boolean isSecondName(String secondName) {
		// TODO Auto-generated method stub
		return Pattern.compile(SECOND_NAME_REGEX).matcher(secondName).find();
	}

	public boolean isTelephone(String telephone) {
		// TODO Auto-generated method stub
		return Pattern.compile(TELEPHONE_REGEX).matcher(telephone).find();
	}

}
