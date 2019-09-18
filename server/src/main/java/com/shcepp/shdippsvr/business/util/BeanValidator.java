package com.shcepp.shdippsvr.business.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by admin on 2018/5/30.
 */
public class BeanValidator {

    /**
     * 验证某个bean的参数
     *
     * @param object 被校验的参数
     * @throws ValidationException 如果参数校验不成功则抛出此异常
     */
    public static <T> void validate(T object) {
        //获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //执行验证
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            for (ConstraintViolation<T> val : constraintViolations) {
                validateError.append(val.getMessage() + " ;");
            }
            throw new ValidationException(validateError.toString());
        }
    }

}
