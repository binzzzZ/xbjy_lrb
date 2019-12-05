package com.lrb.sys.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lrb.sys.constants.SysConstant;
import com.lrb.sys.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

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

        HttpSession session = request.getSession();

        //拦截index,jsp
        if (uri.endsWith("index.jsp")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (SysConstant.COOKIE_LOGIN_USER.equals(cookie.getName())) {
                        //取出cookie中的信息，解码
                        String jsonStr = URLDecoder.decode(cookie.getValue(), "utf-8");
                        User loginUser = JSON.parseObject(jsonStr, new TypeReference<User>() {
                        });
                        //将json字符串转成对象存入session中
                        session.setAttribute(SysConstant.SESSION_LOGIN_NAME, loginUser);

                        //登录成功
                        response.sendRedirect("/view/common/home.jsp");
                    }
                }
            }
        } else if (uri.endsWith("/sys/login/login") || uri.endsWith("/sys/login/getPic")
                || uri.endsWith("forget.jsp") || uri.endsWith("/sys/user/forget") || uri.endsWith("/sys/email/send")) {
            //直接放行登陆验证请求,图片验证码请求,忘记密码请求，邮件验证码请求
        } else {
            //判断session中有没有登录信息
            Object obj = session.getAttribute(SysConstant.SESSION_LOGIN_NAME);
            //非法登陆，回到登录页面
            if (obj == null) {
                response.sendRedirect("/index.jsp");
            }
        }

        filterChain.doFilter(request, response);
    }
}
