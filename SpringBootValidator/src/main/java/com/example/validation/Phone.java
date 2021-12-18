package com.example.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
	String message() default "{com.example.validation.Phone.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
