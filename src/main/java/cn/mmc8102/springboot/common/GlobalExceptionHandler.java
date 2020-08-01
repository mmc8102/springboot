package cn.mmc8102.springboot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author wangli
 * @date 2020/5/2 12:53
 */
@Slf4j
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler {
    @Autowired
    private I18nService i18nService;

    /**
     * 处理业务异常
     */
    @ExceptionHandler(SystemException.class)
    public ApiResponse handleBaseException(SystemException e, HttpServletResponse response) throws IOException {
        log.error("handleRuntimeException: happen a system exception. ", e);

        ApiResponseEnum responseEnum = e.getResponseEnum();
        if (responseEnum == null) {
            responseEnum = ApiResponseEnum.UNKNOW_ERROR;
        }

        return createResponse(responseEnum, e.getArgs());
    }

    /**
     * 处理未捕获的Exception
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ApiResponse handleException(HttpMessageConversionException e){
        log.error("handleRuntimeException: happen a message convert exception. ", e);
        return createResponse(ApiResponseEnum.UNKNOW_ERROR, null);
    }

    /**
     * 处理未捕获的Exception
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e){
        log.error("handleRuntimeException: happen an unknow exception. ", e);
        return createResponse(ApiResponseEnum.UNKNOW_ERROR, null);
    }

    /**
     * 处理未捕获的RuntimeException
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleRuntimeException(RuntimeException e){
        log.error("handleRuntimeException: happen a RuntimeException. ", e);
        return createResponse(ApiResponseEnum.UNKNOW_ERROR, null);
    }

    private ApiResponse createResponse(ApiResponseEnum responseEnum, Object[] args) {
        String msg = i18nService.lang(responseEnum.getKey(), args);
        HeaderStatus headerStatus = new HeaderStatus(responseEnum.getCode(), msg);
        return new ApiResponse(headerStatus);
    }
}
