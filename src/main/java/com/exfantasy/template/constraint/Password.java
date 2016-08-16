package com.exfantasy.template.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=Password.PasswordValidator.class)
public @interface Password {
	
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	public class PasswordValidator implements ConstraintValidator<Password, String> {
		private final long minLength = 6;
    	private final long maxLength = 12;
    	
    	private final Pattern VALID_PASSWORD_REGEX = Pattern.compile(
    			"^(?!.*[^a-zA-Z0-9])(?=.*\\d)(?=.*[a-zA-Z]).{"+minLength+","+maxLength+"}$",
    			Pattern.CASE_INSENSITIVE);
    	
    	public void initialize(Password arg0) {

    	}

    	public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
    		if (password == null) {
    			return false;
    		}
    		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
    		return matcher.find();
    	}
	}
}
