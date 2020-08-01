package cn.mmc8102.springboot.web.controller;

import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    public Employee get(Long id) {
        return this.employeeService.get(id);
    }

}