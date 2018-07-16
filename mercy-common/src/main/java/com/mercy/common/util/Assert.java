package com.mercy.common.util;

import com.mercy.common.util.exception.CheckedException;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Data Validator
 */
public class Assert {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
    /* Validator entity
     * @Author fcao sunf1ower@126.com
     * @Description //TODO 
     * @Date 11:28 2018/7/10
     * @Param [obj, groups]
     * @return void
     **/
    public static void validateEntity(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation constraint :  constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new CheckedException(msg.toString());
        }
    }

    public static void isBlank(String str,String message){
        if(StringUtils.isBlank(str)){
            throw new CheckedException(message);
        }
    }

    public static void isNull(Object object,String message){
        if(object == null){
            throw new CheckedException(message);
        }
    }

}
