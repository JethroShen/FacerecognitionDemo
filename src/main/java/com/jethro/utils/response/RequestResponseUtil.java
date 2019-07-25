package com.jethro.utils.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * http request response 过滤XSS SQL 数据工具类
 */
public class RequestResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseUtil.class);


    /* *
     * @Description 封装response  统一json返回
     * @Param [encoding, outStr, response]
     * @Return void
     */
    public static void responseWrite(String outStr, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        // OutputStreamWriter osw = null;
        try (OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8")) {
            writer = new PrintWriter(osw, true);
            writer.write(outStr);
            writer.flush();
            writer.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    public static void responseWrite(String outStr, int code, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(code);
        PrintWriter writer = null;
        // OutputStreamWriter osw = null;
        try (OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8")) {
            writer = new PrintWriter(osw, true);
            writer.write(outStr);
            writer.flush();
            writer.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
