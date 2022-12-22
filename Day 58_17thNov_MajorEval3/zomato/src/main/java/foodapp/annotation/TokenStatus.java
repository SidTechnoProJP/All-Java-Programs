package foodapp.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
@Constraint(validatedBy = TokenStatusValidator.class)
public @interface TokenStatus {

    public String message() default "Please provide valid status(ACTIVE,EXPIRED)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}