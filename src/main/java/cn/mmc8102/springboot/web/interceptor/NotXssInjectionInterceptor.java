package cn.mmc8102.springboot.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
                fillResponse(response, String.format("参数 %s 含有非法字符！", paramName));
                return false;
            }
        }
        return true;
        /*NotXssInjection notXssInjection = Utils.getAnnotation(handler, NotXssInjection.class);
        if (notXssInjection != null) {
            if (notXssInjection.value().length != 0) {
                for (final String param: notXssInjection.value()) {
                    String parameter = request.getParameter(param);
                    if (StringUtils.isNotBlank(parameter) && !parameter.equals(Utils.cleanXSS(parameter))) {
                        Utils.fillResponse(response, String.format("参数 %s 含有非法字符！", param));
                        return false;
                    }
                }
            }else {
                Enumeration allParam = request.getParameterNames();
                while (allParam.hasMoreElements()) {
                    String paramName = (String) allParam.nextElement();
                    String parameter = request.getParameter(paramName);
                    if (StringUtils.isNotBlank(parameter) && !parameter.equals(Utils.cleanXSS(parameter))) {
                        Utils.fillResponse(response, String.format("参数 %s 含有非法字符！", paramName));
                        return false;
                    }
                }
            }
        }
        return true;*/
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

    public static void fillResponse(HttpServletResponse response, String msg) throws Exception{
        response.setStatus(200);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.close();
        response.flushBuffer();
    }
}
