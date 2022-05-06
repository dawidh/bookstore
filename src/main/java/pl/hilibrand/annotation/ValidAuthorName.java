package pl.hilibrand.annotation;

import pl.hilibrand.config.AuthorNameConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = AuthorNameConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidAuthorName {
    String message() default "Invalid author name. Forename or surname should start " +
            "from letter ‘A’.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
