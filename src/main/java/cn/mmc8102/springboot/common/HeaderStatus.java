package cn.mmc8102.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 16282
 */
@Data
@AllArgsConstructor
public class HeaderStatus implements Serializable {

    private int code;
    private String desc;

    public static final HeaderStatus SUCCESS = new HeaderStatus(200, "OK");

}
