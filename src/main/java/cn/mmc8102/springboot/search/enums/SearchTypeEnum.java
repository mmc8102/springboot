package cn.mmc8102.springboot.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangli
 * @description 搜索类型枚举
 * @date 2023/06/17 00:14:00
 */
@Getter
@AllArgsConstructor
public enum SearchTypeEnum {

    STORE("storeSearch", "门店搜索");



    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据枚举类型获取枚举
     * @param type
     * @return
     */
    public static SearchTypeEnum getSearchType(String type) {
        if(StringUtils.isEmpty(type)){
            return null;
        }

        for (SearchTypeEnum typeEnum : SearchTypeEnum.values()) {
            if(typeEnum.getType().equals(type)){
                return typeEnum;
            }
        }

        return null;
    }

    /**
     * 判断枚举类型是否包含type
     * @param type
     * @return
     */
    public static Boolean isContainsType(String type){
        if(StringUtils.isBlank(type)){
            return false;
        }
        for (SearchTypeEnum typeEnum : SearchTypeEnum.values()) {
            if(typeEnum.getType().equals(type)){
                return true;
            }
        }

        return false;
    }
}
