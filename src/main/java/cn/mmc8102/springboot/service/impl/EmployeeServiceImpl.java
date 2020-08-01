package cn.mmc8102.springboot.service.impl;

import cn.mmc8102.springboot.domain.Employee;
import cn.mmc8102.springboot.mapper.EmployeeMapper;
import cn.mmc8102.springboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

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
    public Employee get(Long id) {
        return this.employeeMapper.queryById(id);
    }

    @Override
    public List<Employee> list() {
        return this.employeeMapper.list();
    }
}