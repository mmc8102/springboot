package cn.mmc8102.springboot.domain;

import java.util.Date;
import lombok.Data;

/**
 * (Employee)实体类
 *
 * @author wangli
 * @since 2020-06-18 12:16:14
 */
@Data
public class Employee {
    
    private Long id;
    
    private String username;
    
    private String realname;
    
    private String password;
    
    private String tel;
    
    private String email;
    
    private Long deptId;
    
    private Date inputtime;
    
    private Integer state;
    
    private Integer admin;

}