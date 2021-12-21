package cn.mmc8102.springboot.util;

import cn.mmc8102.springboot.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author wangli
 * @description TODO
 * @date 2021/12/21 15:16:00
 */
@Component
@Slf4j
public class FilterUtil {

    private FilterUtil() {
    }

    /**
     * 针对serviceToken校验失败的，直接返回
     * @param response
     * @param apiResponse
     */
    public static void writeResponse(HttpServletResponse response, ApiResponse apiResponse){
        try(
                OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(osw, true);
        ) {
            response.setHeader("Content-Type", "text/plain;charset=utf-8");
            String jsonStr = JsonTool.toJson(apiResponse);
            writer.write(jsonStr);
            writer.flush();
        } catch (IOException e) {
            log.error("写入response异常:", e);
        }
    }
}
