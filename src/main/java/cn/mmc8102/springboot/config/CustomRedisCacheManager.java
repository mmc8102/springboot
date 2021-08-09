package cn.mmc8102.springboot.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;


public class CustomRedisCacheManager extends RedisCacheManager {
    public static final String DEFAULT_SPEL = "#";

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        //名称中存在#标记进行到期时间配置
        if (StringUtils.isNotBlank(name) && name.contains(DEFAULT_SPEL)) {
            String[] SPEL = name.split("#");
            if (StringUtils.isNumeric(SPEL[1])) {
                //配置缓存有效时间 单位默认秒
                return super.createRedisCache(SPEL[0], cacheConfig.entryTtl(Duration.ofSeconds(Integer.valueOf(SPEL[1]))));
            }
        }
        return super.createRedisCache(name, cacheConfig);
    }

}
