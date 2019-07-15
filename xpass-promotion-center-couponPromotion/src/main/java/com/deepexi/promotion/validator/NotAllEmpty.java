package com.deepexi.promotion.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liaoxiaoxin
 * @date 2019/6/3 19:21
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotAllEmptyValidator.class})
public @interface NotAllEmpty {

    String message() default "不能同时为空!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
