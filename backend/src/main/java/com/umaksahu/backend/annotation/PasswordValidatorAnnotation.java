package com.umaksahu.backend.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.umaksahu.backend.validator.PasswordValidator;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidatorAnnotation {
	String message() default "strong password constraints not met. Please create a strong password.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
