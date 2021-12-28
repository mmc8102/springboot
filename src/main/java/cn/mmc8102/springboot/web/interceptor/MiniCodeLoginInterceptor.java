package cn.mmc8102.springboot.web.interceptor;

import cn.mmc8102.springboot.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wangli
 * @Date 2021/12/14 17:24
 * @Description: 登录拦截器
 */
@Slf4j
@Component
public class MiniCodeLoginInterceptor implements HandlerInterceptor {

    public MiniCodeLoginInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //清除所有设置的threadlocal
        UserContext.removeAll();
    }

}
