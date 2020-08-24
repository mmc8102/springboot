package cn.mmc8102.springboot.web.controller;

import cn.mmc8102.springboot.common.ApiResponse;
import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Employee相关控制器
 * @author wangli
 * @since 2020-06-18 12:16:14
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
   
    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get")
    @ResponseBody
    public Employee get(Long id) {
        return this.employeeService.get(id);
    }

    @RequestMapping("add")
    @ResponseBody
    public ApiResponse add(String paramsJson){
        return employeeService.add(paramsJson);
    }
}