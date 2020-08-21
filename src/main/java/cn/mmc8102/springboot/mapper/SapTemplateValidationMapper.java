package cn.mmc8102.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: wangli28
 * @Date: 2020/8/21 10:46
 */
public interface SapTemplateValidationMapper {

    @Select("select template from sap_template_validation where template_id = #{templateId}")
    String getByTemplateId(@Param("templateId") Integer templateId);
}
