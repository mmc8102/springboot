package cn.mmc8102.springboot.service.impl;

import cn.mmc8102.springboot.common.ApiResponse;
import cn.mmc8102.springboot.common.ApiResponseEnum;
import cn.mmc8102.springboot.common.SystemException;
import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.domain.TemplateValidate;
import cn.mmc8102.springboot.mapper.EmployeeMapper;
import cn.mmc8102.springboot.mapper.SapTemplateValidationMapper;
import cn.mmc8102.springboot.service.IEmployeeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangli
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SapTemplateValidationMapper sapTemplateValidationMapper;
    @Autowired
    private Gson gson;

    @Override
    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.employeeMapper.deleteById(id) > 0;
    }
    
    @Override
    @Cacheable(value = "employee", key = "#id")
    public Employee get(Long id) {
        return this.employeeMapper.queryById(id);
    }

    @Override
    public List<Employee> list() {
        return this.employeeMapper.list();
    }

    @Override
    public ApiResponse add(String paramsJson) {
        if(StringUtils.isNotBlank(paramsJson)) {
            JsonObject paramsJsonObject = gson.fromJson(paramsJson, JsonObject.class);
            //校验数据
            paramsValidation(paramsJsonObject);
            //校验是否已经创建
            //对象封装
            Employee employee = gson.fromJson(paramsJsonObject.get("data"), Employee.class);
            //保存
            employeeMapper.insert(employee);
            //

            return new ApiResponse();
        }
        return new ApiResponse(ApiResponseEnum.PARAM_EXCEPTION);
    }

    /**
     * 可配置化动态校验数据
     */
    private void paramsValidation(JsonObject paramsJsonObject){
        int templateId = paramsJsonObject.get("type").getAsInt();
        if(templateId > 0){
            //根据templateId获取验证模板
            String template = sapTemplateValidationMapper.getByTemplateId(templateId);
            if(StringUtils.isNotBlank(template)){
                JsonObject templateJsonObject = gson.fromJson(template, JsonObject.class);
                Set<Map.Entry<String, Object>> templateEntrySet = gson.fromJson(templateJsonObject, Map.class).entrySet();
                Map paramsDataMap = gson.fromJson(paramsJsonObject.get("data"), Map.class);
                //校验参数字段是否完全匹配
                for (Map.Entry<String, Object> entry : templateEntrySet) {
                    if(!paramsDataMap.containsKey(entry.getKey())){
                        throw new SystemException(ApiResponseEnum.PARAM_EXCEPTION);
                    }
                }
                Set<Map.Entry<String, Object>> entrySet = paramsDataMap.entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    TemplateValidate templateValidate = gson.fromJson(templateJsonObject.get(key), TemplateValidate.class);
                    if(templateValidate != null){
                        //判断是否为空
                        if("true".equals(templateValidate.getNotNull())){
                            if(value == null || StringUtils.isBlank(String.valueOf(value))){
                                throw new SystemException(ApiResponseEnum.PARAM_EXCEPTION);
                            }
                        }
                        //判断长度是否超过规定值
                        if(templateValidate.getLength() != null){
                            if(((String) value).length() < templateValidate.getLength()){
                                throw new SystemException(ApiResponseEnum.PARAM_EXCEPTION);
                            }
                        }
                        //判断格式是否正确
                    }
                }
            }
        }
    }

}