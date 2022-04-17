package com.yinggu.plane.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandler
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/16 14:26
 * Version 1.0
 */
public class LoginHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect("/flight/login");
            return false;
        }
        return true;
    }
}
