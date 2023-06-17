package cn.mmc8102.springboot.search.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangli
 * @description TODO
 * @date 2023/06/17 00:27:00
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS("success", "成功", "成功"),
    EXCEPTION("systemException", "系统异常", "系统异常");

    private String code;
    private String message;

    private String description;
}
