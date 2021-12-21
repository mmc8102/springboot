package cn.mmc8102.springboot.web.interceptor;

import cn.mmc8102.springboot.common.ApiResponse;
import cn.mmc8102.springboot.common.ApiResponseEnum;
import cn.mmc8102.springboot.util.FilterUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 校验XSS攻击
 *
 * @author wangli
 * @date 2018/07/10
 */
@Component
public class NotXssInjectionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration allParam = request.getParameterNames();
        while (allParam.hasMoreElements()) {
            String paramName = (String) allParam.nextElement();
            String parameter = request.getParameter(paramName);
            if (StringUtils.isNotBlank(parameter) && !parameter.equals(cleanXSS(parameter))) {
                FilterUtil.writeResponse(response, new ApiResponse<>(ApiResponseEnum.PARAMS_ILLEGAL, "参数含有非法字符！"));
                return false;
            }
        }
        return true;
    }
    public static String cleanXSS(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
}
