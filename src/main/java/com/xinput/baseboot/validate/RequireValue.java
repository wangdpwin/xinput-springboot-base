package com.xinput.baseboot.validate;

import com.xinput.bleach.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * 使用方式 @Value({"dev", "pre", "prod"})
 *
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 17/1/18 下午3:12
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RequireValue.Check.class)
public @interface RequireValue {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();

    class Check implements ConstraintValidator<RequireValue, Object> {

        private String[] values;

        @Override
        public void initialize(RequireValue value) {
            values = value.value();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value == null || StringUtils.isNullOrEmpty(String.valueOf(value))) {
                return false;
            }
            return Arrays.asList(values).contains(value);
        }

    }
}
