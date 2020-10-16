package cn.mmc8102.springboot.common;

import cn.mmc8102.springboot.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author wangli
 * 后台调用： 注入i18nService， 直接I18nService.lang(key)即可
 * ErrorCode调用：I18nService.lang(GlobalErrorCodeEnum.COMMON_SUCCESS.getDesc())
 */
@Component
public class I18nService {

    @Autowired
    private MessageSource messageSource;

    /**
     * 国际化
     * @param key properties里的key
     * @return
     */
    public String lang(String key){
        return this.lang(key,new Object[]{});
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param defaultMessage 不存在时默认输出
     * @return
     */
    public String lang(String key,String defaultMessage){
        return this.lang(key,null,defaultMessage);
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param defaultMessage 不存在时默认输出
     * @param locale 环境
     * @return
     */
    public  String lang(String key,String defaultMessage,Locale locale){
        return this.lang(key,null,defaultMessage,locale);
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param locale 环境
     * @return
     */
    public  String lang(String key,Locale locale){
        return this.lang(key,null,"",locale);
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @return
     */
    public String lang(String key,Object[] args){
        return this.lang(key,args,"");
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param locale 环境
     * @return
     */
    public String lang(String key,Object[] args,Locale locale){
        return this.lang(key,args,"",locale);
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param defaultMessage 不存在时默认输出
     * @return
     */
    public String lang(String key,Object[] args,String defaultMessage){
        Locale locale = LocaleContextHolder.getLocale();
        return this.lang(key,args, defaultMessage,locale);
    }

    /**
     * 国际化
     * @param key properties里的key
     * @param args properties里value里的参数，替换其中{0} {1}...
     * @param defaultMessage 不存在时默认输出
     * @param locale 环境
     * @return
     */
    public String lang(String key,Object[]args,String defaultMessage,Locale locale){
        if(messageSource == null){
            messageSource = BeanUtils.getBean(MessageSource.class);
        }
        String message = messageSource.getMessage(key, args, defaultMessage, locale);
        return messageSource.getMessage(key,args, defaultMessage,locale);
    }
}
