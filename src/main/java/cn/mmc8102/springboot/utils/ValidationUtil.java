package cn.mmc8102.springboot.utils;

import lombok.Data;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangli
 * @date 2022/4/11
 * @desc 参数统一验证，需配合注解使用
 */
public class ValidationUtil {
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false)
        .buildValidatorFactory().getValidator();

    private static ExecutableValidator executableValidator = validator.forExecutables();

    /**
     * 校验对象,如果对象是null,默认不校验
     *
     * @param t      bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T t, Class<?>... groups) {
        ValidResult result = new ValidResult();
        if (t == null) {
            result.setHasErrors(true);
            result.addError("param cannot be null", "传参不能为空");
            return result;
        }
        Set<ConstraintViolation<T>> violationSet = validator.validate(t, groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return result;
    }

    /**
     * 校验方法入参
     *
     * @param service
     * @param method
     * @param args
     * @return
     */
    public static ValidResult validateParameters(Object service, Method method, Object[] args) {
        ValidResult result = new ValidResult();
        if (service == null || method == null || args == null) {
            return result;
        }
        Set<ConstraintViolation<Object>> constraintViolations =
            executableValidator.validateParameters(service, method, args);
        if (constraintViolations.isEmpty()) {
            return result;
        }
        for (ConstraintViolation<Object> violation : constraintViolations) {
            result.addError(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return result;
    }

    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        ValidResult result = new ValidResult();
        if (obj == null) {
            result.setHasErrors(true);
            result.addError("Object cannot be null", "传参不能为空");
            return result;
        }
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(propertyName, violation.getMessage());
            }
        }
        return result;
    }

    /**
     * 校验结果类
     */
    @Data
    public static class ValidResult {

        /**
         * 是否有错误
         */
        private boolean hasErrors;

        /**
         * 错误信息
         */
        private List<ErrorMessage> errors;

        public ValidResult() {
            this.errors = new ArrayList<>();
        }

        public boolean hasErrors() {
            return hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        /**
         * 获取所有验证信息
         *
         * @return 集合形式
         */
        public List<ErrorMessage> getAllErrors() {
            return errors;
        }

        /**
         * 获取所有验证信息
         *
         * @return 字符串形式
         */
        public String getErrors() {
            StringBuilder sb = new StringBuilder();
            for (ErrorMessage error : errors) {
                sb.append(error.getPropertyName()).append(":").append(error.getMessage()).append(" ");
            }
            return sb.toString();
        }

        public void addError(String propertyName, String message) {
            this.errors.add(new ErrorMessage(propertyName, message));
        }
    }

    @Data
    public static class ErrorMessage {
        /**
         * 字段属性名
         */
        private String propertyName;

        /**
         * 错误详情
         */
        private String message;

        public ErrorMessage() {
        }

        public ErrorMessage(String propertyName, String message) {
            this.propertyName = propertyName;
            this.message = message;
        }
    }
}
