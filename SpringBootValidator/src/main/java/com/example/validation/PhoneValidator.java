package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final Pattern PATTERN = Pattern.compile("[0-9]+");

	@Override
	public void initialize(Phone constraintAnnotation) {

	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext cxt) {
		return field != null && PATTERN.matcher(field).matches()
				&& (field.length() > 8) && (field.length() < 14);
	}
}
