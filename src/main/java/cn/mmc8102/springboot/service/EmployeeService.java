package cn.mmc8102.springboot.service;

import cn.mmc8102.springboot.domain.Employee;
import java.util.List;

/**
 * Employee相关服务
 * @author wangli
 * @since 2020-06-18 12:16:14
 */
public interface EmployeeService {

    /**
     * 新增数据
     * @param employee 实例对象
     * @return 实例对象
     */
    int insert(Employee employee);

    /**
     * 修改数据
     * @param employee 实例对象
     * @return 实例对象
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Employee get(Long id);

    /**
     * 查询列表
     * @return 对象列表
     */
    List<Employee> list();

}