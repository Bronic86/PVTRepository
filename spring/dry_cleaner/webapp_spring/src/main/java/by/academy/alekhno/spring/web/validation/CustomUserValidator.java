package by.academy.alekhno.spring.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.academy.alekhno.vo.User;

public class CustomUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;

	}

	private void validatePostCode(String postCode, Errors errors) {

		if (isValidString(postCode) && isNotBirminghamPostCode(postCode)) {
			errors.rejectValue("postCode", "AddressValidator.postCode.notBirmingham",
					"Not a Birmingham Post Code");
		}
	}

	private boolean isValidString(String str) {

		return isNotNull(str) && (str.length() > 0);
	}

	private boolean isNotNull(String postCode) {

		return postCode != null;
	}

	/** The first character of the Birmingham post code is 'B' */
	private boolean isNotBirminghamPostCode(String postCode) {

		char val = postCode.charAt(0);
		return val != 'B';
	}
}
