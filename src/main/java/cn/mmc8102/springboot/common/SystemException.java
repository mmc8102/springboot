package cn.mmc8102.springboot.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangli
 * 自定义异常
 */
@Data
@NoArgsConstructor
public class SystemException extends RuntimeException {

    private ApiResponseEnum responseEnum;

    private Object[] args;

    public SystemException(ApiResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public SystemException(ApiResponseEnum responseEnum, Object... args) {
        this.responseEnum = responseEnum;
        this.args = args;
    }
}
