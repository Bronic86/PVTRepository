package by.academy.alekhno.spring.web.validation;

import java.util.regex.Pattern;

public class ValidationImpl {

	private static final String LOGIN_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	private static final String PASSWORD_REGEX = "^[\\w\\d]*$";
	private static final String FIRST_NAME_REGEX = "[A-Za-z0-9_а-яА-ЯёЁ]{1,30}";
	private static final String SECOND_NAME_REGEX = "[A-Za-z0-9_а-яА-ЯёЁ]{1,30}";
	private static final String TELEPHONE_REGEX = "^[0-9]{12}$";
	private static final String DIGIT_REGEX = "^[0-9]{1,5}$";

	public boolean isLogin(String login) {
		return Pattern.compile(LOGIN_REGEX).matcher(login).find();
	}

	public boolean isPassword(String password) {
		return Pattern.compile(PASSWORD_REGEX).matcher(password).find();
	}

	public boolean isFirstName(String firstName) {
		return Pattern.compile(FIRST_NAME_REGEX).matcher(firstName).find();
	}

	public boolean isSecondName(String secondName) {
		return Pattern.compile(SECOND_NAME_REGEX).matcher(secondName).find();
	}

	public boolean isTelephone(String telephone) {
		return Pattern.compile(TELEPHONE_REGEX).matcher(telephone).find();
	}

	public boolean isNumber(String clother_id_param) {
		return Pattern.compile(DIGIT_REGEX).matcher(clother_id_param).find();
	}

}
