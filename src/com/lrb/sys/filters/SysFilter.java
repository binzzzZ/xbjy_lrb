package com.lrb.sys.filters;

import com.lrb.sys.constants.SysConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/11/29 17:26
 * @Description
 */
@WebFilter("/*")
public class SysFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//获取请求路径
        String uri = request.getRequestURI();

        //直接放行index.jsp,登陆验证请求,图片验证码请求
        if (uri.endsWith("index.jsp") || uri.endsWith("/sys/login/login") || uri.endsWith("/sys/login/getPic")
        ||uri.endsWith("forget.jsp")||uri.endsWith("/sys/user/forget")||uri.endsWith("/sys/email/send")) {

        } else {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_NAME);
            //非法登陆
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }
        filterChain.doFilter(request, response);
    }
}
