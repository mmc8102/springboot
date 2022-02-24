package cn.mmc8102.springboot.common;

import cn.mmc8102.springboot.util.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * api接口统一返回格式
 * @author wangli
 */
@Data
@Accessors(chain = true)
public class ApiResponse<T>{

    private HeaderStatus header = HeaderStatus.SUCCESS;
    @JsonIgnore
    private I18nService i18nService;

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

    public ApiResponse(ApiResponseEnum apiResponseEnum) {
        if(i18nService == null){
            i18nService = BeanUtils.getBean(I18nService.class);
        }
        this.header = new HeaderStatus(apiResponseEnum.getCode(), i18nService.lang(apiResponseEnum.getKey(),apiResponseEnum.getStatement()));
    }

    public ApiResponse(ApiResponseEnum apiResponseEnum, Object... args) {
        if(i18nService == null){
            i18nService = BeanUtils.getBean(I18nService.class);
        }
        this.header = new HeaderStatus(apiResponseEnum.getCode(), i18nService.lang(apiResponseEnum.getKey(),args,apiResponseEnum.getStatement()));
    }
}
