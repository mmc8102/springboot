package cn.mmc8102.springboot.common;

import lombok.Data;

/**
 * @author wangli
 * 自定义异常
 */
@Data
public class SystemException extends RuntimeException {

    private ApiResponseEnum responseEnum;

    private Object[] args;

    public SystemException() { }

    public SystemException(ApiResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

}
