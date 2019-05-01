package com.yibo.springbootbeanvalidation.validation.constraints;

import com.yibo.springbootbeanvalidation.validation.ValidCardNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: huangyibo
 * @Date: 2018/12/9 19:26
 * @Description:
 *
 * 合法卡号校验
 * 需求：通过员工的卡号来校验，需要通过工号的前缀和后缀来判断
 * 前缀必须以“YIBO-”开头
 * 后缀必须是数字
 * 需要通过Bean Validator来校验
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCardNumberConstraintValidator.class})
public @interface ValidCardNumber {

    String message() default "{com.yibo.bean.validation.invalid.cardNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
