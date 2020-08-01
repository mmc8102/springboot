package cn.mmc8102.springboot.common;

import lombok.Data;

/**
 * api接口统一返回格式
 * @author 16282
 */
@Data
public class ApiResponse<T>{

    private HeaderStatus header = HeaderStatus.SUCCESS;

    private T body;

    public ApiResponse() {
    }

    public ApiResponse(T body) {
        this.body = body;
    }

    public ApiResponse(HeaderStatus header) {
        this.header = header;
    }

    public ApiResponse(int code, String desc) {
        this.header = new HeaderStatus(code, desc);
    }

    public ApiResponse(T body, HeaderStatus header) {
        this.body = body;
        this.header = header;
    }
}
