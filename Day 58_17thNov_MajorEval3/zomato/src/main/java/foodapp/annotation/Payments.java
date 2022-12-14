package foodapp.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentValidator.class)
public @interface Payments {
    public String message() default "Please provide valid payment Type(CREDITCARD ,DEBITCARD,CASHONDELIVARY,UPI,NETBANKING)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
