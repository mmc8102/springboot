package cn.mmc8102.springboot.common;

/**
 * @author wangli
 */
public enum ApiResponseEnum {

    /**
     * 系统统一的返回code，是1000以下（不包含1000）
     */
    SUCCESS(200, "", "system.success"),

    ERROR(999, "系统异常", "system.error"),

    UN_LOGIN_ERROR(401, "", "system.unlogin.error"),

    UNKNOW_ERROR(500, "", "response.unknow.error"),

    RUN_EXCEPTION(501, "Java exception", "system.exception.run"),
    PARAM_EXCEPTION(502, "参数异常", "param.error"),
    NO_ACCESS_OPERATE(503, "无权操作", "no.access.operate"),
    INTERFACE_FAIL(504, "接口调用失败:{0}", "interface.fail"),
    EXCEPTION(505, "", "exception"),

    /**
     * member相关API返回,code范围是[1000,1999]
     */
    MEMBER_NOT_EXISTS(1000, "", "member.user.not.exists"),

    ;
    /**
     * 错误码
     */
    private int code;

    private String statement;

    /**
     * 国际化消息key
     */
    private String key;

    ApiResponseEnum(int code, String statement, String key) {
        this.code = code;
        this.statement = statement;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
