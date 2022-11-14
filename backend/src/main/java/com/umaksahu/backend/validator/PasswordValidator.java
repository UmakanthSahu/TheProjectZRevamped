package com.umaksahu.backend.validator;

import java.nio.CharBuffer;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.umaksahu.backend.annotation.PasswordValidatorAnnotation;

public class PasswordValidator implements ConstraintValidator<PasswordValidatorAnnotation, char[]> {

	@Override
	public boolean isValid(char[] value, ConstraintValidatorContext context) {
		return 
		Pattern.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,50}$", CharBuffer.wrap(value));
	}

}
