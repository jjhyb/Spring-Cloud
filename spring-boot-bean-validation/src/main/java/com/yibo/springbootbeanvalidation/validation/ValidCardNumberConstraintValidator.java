package com.yibo.springbootbeanvalidation.validation;

import com.yibo.springbootbeanvalidation.validation.constraints.ValidCardNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author: huangyibo
 * @Date: 2018/12/9 19:41
 * @Description:
 */
public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber,String> {
    @Override
    public void initialize(ValidCardNumber validCardNumber) {

    }

    /**
     * 合法卡号校验
     * 需求：通过员工的卡号来校验，需要通过工号的前缀和后缀来判断
     * 前缀必须以“YIBO-”开头
     * 后缀必须是数字
     * 需要通过Bean Validator来校验
     *
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //前半部分和后半部分
        String[] parts = StringUtils.delimitedListToStringArray(value,"-");
        //上面为什么不用String.split()方法，原因在于该方法使用了正则表达式，其次是空指针(NPE)保护不够
        //如果在依赖中，没有StringUtils.delimitedListToStringArray API的话，可以使用
        //还有Apache commons-lang StringUtils
        // (JDK里面提供)StringTokenizer(类似于枚举Enumeration API，这点上有些不足)
        if(parts.length != 2){

            //如果上面用的是apache的StringUtils进行分割的话，这里判断要用ArrayUtils.getLength(parts) != 2,防止空指针问题

            return false;
        }

        String prefix = parts[0];
        String suffix = parts[1];

        boolean isValiaPrefix = Objects.equals("YIBO",prefix);
        boolean isValidSuffix = org.apache.commons.lang3.StringUtils.isNumeric(suffix);

        return isValiaPrefix && isValidSuffix;
    }
}
