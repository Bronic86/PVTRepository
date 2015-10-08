package by.academy.alekhno.spring.web.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import by.academy.alekhno.spring.web.form.OrderForm;

@Component
public class CustomOrderValidator implements Validator {
	private static final String DIGIT_REGEX = "^[1-9][0-9]{0,1}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderForm.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		OrderForm orderForm = (OrderForm) object;

		validateID(orderForm.getQuantity(), errors);

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity",
		// "error.quantity",
		// "Quantity is required.");
	}

	private void validateID(String quantity, Errors errors) {
		if (!isNumberPattern(quantity)) {
			errors.rejectValue("quantity", "CustomOrderValidator.OrderForm.quantity",
					"The quantity must be more 1 and less 100");
		}
	}

	private boolean isNumberPattern(String quantity) {
		return Pattern.matches(DIGIT_REGEX, quantity);
	}

}
