package cn.mmc8102.springboot.mapper;

import cn.mmc8102.springboot.domain.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Employee)表数据库访问层
 *
 * @author wangli
 * @since 2020-06-18 12:16:14
 */
public interface EmployeeMapper {

    /**
     * 新增数据
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);

    /**
     * 修改数据
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    Employee queryById(Long id);

    /**
     * 查询列表
     * @return 对象列表
     */
    List<Employee> list();
}