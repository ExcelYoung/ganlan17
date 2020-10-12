package com.etc;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {


    //目标业务方法执行之前调用
    //如果返回true,表示向后执行,如果返回false表示不向后执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler:handler:"+handler);
        Object user=request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect("login.html");
            return false;
        }
        //放行
        return true;
    }


    //目标方法执行之后调用
    //handler:表示目标业务对象,modelAndView目标业务对象执行之后的返回值
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler:handler:"+handler+",modelAndView"+modelAndView);
    }


    //页面显示完成后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion:handler:"+handler);
    }
}
