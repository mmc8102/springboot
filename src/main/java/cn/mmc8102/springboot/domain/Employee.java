package cn.mmc8102.springboot.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Employee)实体类
 *
 * @author wangli
 * @since 2020-06-18 12:16:14
 */
@Data
public class Employee implements Serializable {
    
    private Long id;
    
    private String username;
    
    private String password;

    private Date createTime;

}