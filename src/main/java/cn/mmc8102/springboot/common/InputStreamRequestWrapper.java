package cn.mmc8102.springboot.common;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author wangli
 * @description request封装类  用于读取post body
 * @date 2023/11/06 22:20:00
 */
public class InputStreamRequestWrapper extends HttpServletRequestWrapper {

    private ByteArrayInputStream bais;
    private String readerLine;
    private byte[] content;
    private boolean isRawData = false;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public InputStreamRequestWrapper(HttpServletRequest request) {
        super(request);
        try{
            if (request.getContentType() != null) {
                boolean isMultipartFormData = request.getContentType().toLowerCase().contains("multipart/form-data");
                boolean isUrlencodedFormData = request.getContentType().toLowerCase().contains("application/x-www-form-urlencoded");
                boolean octetData = request.getContentType().toLowerCase().contains("application/octet-stream");
                boolean javaBin = request.getContentType().toLowerCase().contains("application/javabin");

                isRawData = !isMultipartFormData && !isUrlencodedFormData && !octetData && !javaBin;
            }

            if (isRawData) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(5120);
                try {
                    ServletInputStream sis = request.getInputStream();
                    IOUtils.copy(sis, baos);
                    content = baos.toByteArray();
                    bais = new ByteArrayInputStream(content);
                } catch (Exception e) {
                    BufferedReader reader=request.getReader();
                    readerLine=reader.readLine();
                }
            }
        }catch (Exception e){
            //保障稳定性
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (!isRawData) {
            return super.getInputStream();
        }

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {

            }
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (!isRawData) {
            return super.getReader();
        }

        String charset = getCharacterEncoding();
        if (charset == null) {
            charset = "utf-8";
        }

        return new BufferedReader(new InputStreamReader(bais, charset));
    }

    public String getContent() {
        if (content == null) {
            return null;
        }

        String charset = getCharacterEncoding();
        if (charset == null) {
            charset = "utf-8";
        }
        try {
            if(readerLine!=null && readerLine.length()>0){
                return readerLine;
            }
            String contentStr=new String(content, charset);
            if(contentStr != null && contentStr.length()>5120){
                return contentStr.substring(0,5120);
            }else{
                return contentStr;
            }
        } catch (Exception e) {
            //不抛异常
            return  null;
        }
    }

}
