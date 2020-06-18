package com.keith.rent.core.interceptor;

import com.keith.rent.core.constants.SysConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在执行Controller层方法之前进行请求的参数处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            this.proceError(response);
            return false;
        }
        /**这里cookie的名字在UserAuthController里面定义了**/
        String token = "";
        for (Cookie cookie : cookies) {
            if (SysConstants.cookieName.equals(cookie.getName())) {
                token = cookie.getName();
                break;
            }
        }
        if (token == null) {
            this.proceError(response);
            return false;
        }
        /** 在登录的时候我们设置了 token-> 用户信息 的映射关系
         根据token从redis或者mysql中拿到用户信息，然后设置到requst请求中，供后续请求使用
         分布式session就这样实现了*/
        request.setAttribute("userInfo", null);
        return true;
    }

    private void proceError(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("请登录");
    }

}
