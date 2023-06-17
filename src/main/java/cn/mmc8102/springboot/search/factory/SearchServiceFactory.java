package cn.mmc8102.springboot.search.factory;

import cn.mmc8102.springboot.search.strategy.AbstractSearchStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangli
 * @description 策略工厂
 * @date 2023/06/16 23:57:00
 */
@Component
public class SearchServiceFactory {

    @Resource
    private Map<String, AbstractSearchStrategy> instanceMap;

    /**
     * 获取具体处理策略类
     * @param searchType
     * @return
     */
    public AbstractSearchStrategy getInstance(String searchType){
        return instanceMap.get(searchType);
    }
}
