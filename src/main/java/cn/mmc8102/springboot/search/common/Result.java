package cn.mmc8102.springboot.search.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangli
 * @description TODO
 * @date 2023/06/17 00:21:00
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private String errorCode;

    private String errorMessage;

    private Boolean isSuccess;

    private T data;

    private Map<String, Object> extendInfo;

    public Result(){

    }

    public Result(String errorCode, String errorMessage, Boolean isSuccess){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public Result(ResultCode resultCode, boolean isSuccess){
        this.errorCode = resultCode.getCode();
        this.errorMessage = resultCode.getMessage();
        this.isSuccess = isSuccess;
    }

    public static <T> Result<T> success(){
        return new Result<>(ResultCode.SUCCESS, true);
    }

    public static <T> Result<T> success(T data){
        return (new Result(ResultCode.SUCCESS, Boolean.TRUE)).setData(data);
    }

    public static <T> Result<T> error(ResultCode resultCode){
        return new Result(resultCode, Boolean.FALSE);

    }

}
