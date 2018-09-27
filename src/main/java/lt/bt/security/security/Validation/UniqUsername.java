package lt.bt.security.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = lt.bt.security.security.validation.UniqUsernameValidator.class)
public @interface UniqUsername {
    String message() default "Exist!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
