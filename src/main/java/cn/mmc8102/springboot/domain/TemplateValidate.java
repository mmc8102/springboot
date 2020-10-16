package cn.mmc8102.springboot.domain;

import lombok.Data;

/**
 * @Author: wangli
 * @Date: 2020/8/21 10:11
 * 配置化验证模板
 */
@Data
public class TemplateValidate {
    /**
     * 字段名
     */
    private String filed;
    /**
     * 非空
     */
    private String notNull;
    /**
     * 长度
     */
    private Integer length;
    /**
     * 正则验证
     */
    private String formate;

}
