package cn.mmc8102.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期相关工具类
 */
public class DateUtils {

    /**
     * 按照指定格式, 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern){
        if(date == null || StringUtils.isBlank(pattern)){
            return Strings.EMPTY;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return asLocalDateTime(date).format(formatter);
    }

    public static LocalDateTime asLocalDateTime(Date date){
        if(date == null){
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
