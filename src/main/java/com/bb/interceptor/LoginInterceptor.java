package com.bb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by becky_yyj
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        if(!(uri.contains("Login")||uri.contains("login")||uri.contains("register"))){
            if(request.getSession().getAttribute("user")!=null){
                return true;
            }
            else if(uri.contains("css")||uri.contains("images")||uri.contains("js"))
                return true;
            else
                response.sendRedirect(request.getContextPath()+"/user/toLogin.action");
        }
        else
            //登录请求，放行
            return true;
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
