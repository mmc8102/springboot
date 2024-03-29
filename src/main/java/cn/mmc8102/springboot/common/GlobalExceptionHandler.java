package cn.mmc8102.springboot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active}")
    private String env;


    /*@ExceptionHandler({UnauthorizedException.class, UnauthenticatedException.class})
    public ApiResponse unauthorizedException(Exception e){
        log.error(e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.NO_ACCESS_OPERATE);
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ApiResponse unauthenticatedException(UnknownAccountException e){
        log.error(e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.NON_EXISTENT_LOGIN_INFO);
    }*/

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    ApiResponse handleMiMemberException(SystemException e){
        log.error(e.getMessage(), e);
        return new ApiResponse(e.getResponseEnum(), e.getArgs());
    }

    /**
     * 处理参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    ApiResponse handleMiMemberException(MissingServletRequestParameterException e){
        log.error(e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.OPERATE_EXCEPTION, new Object[]{e.getMessage()});
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getMessage(), e);
        ApiResponse response = new ApiResponse();
        response.setBody(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return response;
    }

    @ExceptionHandler({BindException.class})
    public ApiResponse validException(BindException e){
        log.warn("validException errorMsg {} e {}", e.getMessage(), e);
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.add(fieldError.getField().concat(fieldError.getDefaultMessage()));
        }
        return new ApiResponse().setBody(errors.toString());
    }

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e){
        log.error(e.getMessage(), e);
        if (Constants.DEFAULT_ENV_DEV.equals(env) || Constants.DEFAULT_ENV_TEST.equals(env)) {
            return new ApiResponse<>(ApiResponseEnum.OPERATE_EXCEPTION, e.getMessage());
        }
        return new ApiResponse(ApiResponseEnum.UNKNOW_ERROR);
    }



}
