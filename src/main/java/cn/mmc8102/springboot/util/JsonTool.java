package cn.mmc8102.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * @author wangli
 * @Date: 2020/8/21 20:20
 * jackson处理json相关的工具类
 */
@Slf4j
public class JsonTool {
    public static final ObjectMapper mapper = new ObjectMapper();
    private JsonTool(){}
    /**
     * 对象转换为json字符串
     * @param obj
     * @return
     */
    @Nullable
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化出错：{}, e:{}", obj, e);
            return null;
        }
    }

    @Nullable
    public static <T> T toObject(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json解析出错：{}, e:{}", json, e);
            return null;
        }
    }

    @Nullable
    public static <E> List<E> toList(String json) {
        try {
            //return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
            return mapper.readValue(json, new TypeReference<List<E>>(){});
        } catch (IOException e) {
            log.error("json解析出错：{}, e:{}", json, e);
            return null;
        }
    }


}
