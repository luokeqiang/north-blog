package com.luoke.iceblog.common.util;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.luoke.iceblog.common.vo.ResponseVo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet 工具类，主要用于操作HttpRequest对象，HttpSession对象，HttpResponse对象
 * @author luoke
 * @date 2021/11/3 0:05
 */
public class ServletUtil {

    /**
     *  获取{@link HttpServletRequest} 对象
     * @return HttpServletRequest对象
     */
    public static HttpServletRequest getRequest(){
        return Optional.ofNullable(getRequestAttributes())
                .map(ServletRequestAttributes::getRequest)
                .orElse(null);
    }

    /**
     *  获取{@link HttpServletResponse} 对象
     * @return HttpServletResponse 对象
     */
    public static HttpServletResponse getResponse() {
        return Optional.ofNullable(getRequestAttributes())
                .map(ServletRequestAttributes::getResponse)
                .orElse(null);
    }

    /**
     *  获取{@link HttpSession} 对象
     * @return HttpSession对象
     */
    public static HttpSession getSession() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getSession)
                .orElse(null);
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static void renderJsonString(String message,HttpServletResponse response) {
        renderJsonString(HttpStatus.HTTP_OK,message,response);
    }
    public static void renderJsonString(int code,String message,HttpServletResponse response) {
        try {
            response.setStatus(code);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void renderJsonString(int code,ResponseVo responseVo,HttpServletResponse response) {
        try {
            response.setStatus(code);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSONUtil.toJsonStr(responseVo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
