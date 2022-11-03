package com.umaksahu.backend.validator;

import java.nio.CharBuffer;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.umaksahu.backend.annotation.PasswordValidatorAnnotation;

public class PasswordValidator implements ConstraintValidator<PasswordValidatorAnnotation, char[]> {

	@Override
	public boolean isValid(char[] value, ConstraintValidatorContext context) {
		return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
				CharBuffer.wrap(value));
	}

}
