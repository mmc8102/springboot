package cn.mmc8102.springboot.config;

import cn.mmc8102.springboot.web.interceptor.NotXssInjectionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 系统配置类
 * @author wangli
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NotXssInjectionInterceptor()).addPathPatterns("/**");
    }
}
