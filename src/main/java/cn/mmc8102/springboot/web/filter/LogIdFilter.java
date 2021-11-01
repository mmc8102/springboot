package cn.mmc8102.springboot.web.filter;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 将Nginx生成的LogId注入到每个请求中
 * @author wangli
 * @date 2018/07/10
 */
public class LogIdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String nginxLogId = request.getHeader("logid");
            if (StringUtils.isEmpty(nginxLogId)) {
                MDC.put("MDC_TRACE_ID", DateUtil.format(new Date(), "HHmmss") + RandomStringUtils.randomNumeric(4));
            } else {
                MDC.put("MDC_TRACE_ID", nginxLogId);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
