package cn.mmc8102.springboot.domain;

import lombok.Data;

/**
 * @Author: wangli
 * @Date: 2020/8/21 10:47
 */
@Data
public class SapTemplateValidation {
    private Long id;
    private Integer templateId;
    private String template;
    private Long createTime;
    private Long updateTime;
    private Integer sale;
}
