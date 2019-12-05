package com.lrb.sys.controller;

import com.alibaba.fastjson.JSON;
import com.lrb.sys.constants.SysConstant;
import com.lrb.sys.entity.User;
import com.lrb.sys.service.impl.UserServiceImpl;
import com.lrb.sys.utils.ImgCodeUtil;
import com.lrb.sys.utils.MDUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/4 17:43
 * @Description
 */
@WebServlet("/sys/login/*")
public class LoginServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * @Description: 验证登录账号密码
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/4 17:54
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String picCode = request.getParameter("picCode");
        String remember = request.getParameter("remember");

        HttpSession session = request.getSession();

        //图片验证码
        Object obj = session.getAttribute(SysConstant.SESSION_PIC_CODE_NAME);
        if (obj == null || !obj.toString().equalsIgnoreCase(picCode)) {
            //验证码不正确
            response.sendRedirect("/index.jsp");
            return;
        }

        User user = new User();
        user.setAccount(account);
        //密码加密
        user.setPassword(MDUtil.md5(password));

        List<User> list = userService.checkLogin(user);
        if (list == null || list.size() != 1) {
            //登录失败，回到登录界面
            response.sendRedirect("/index.jsp");
            return;
        } else {
            User loginUser = list.get(0);
            //验证通过，登录信息存入Session
            session.setAttribute(SysConstant.SESSION_LOGIN_NAME, loginUser);

            //在线人数
            ServletContext application = getServletContext();
            Object countObj = application.getAttribute(SysConstant.APPLICATION_LOGIN_COUNT);
            int count = 1;
            if (countObj != null) {
                count = Integer.valueOf(countObj.toString()) + 1;
            }
            application.setAttribute(SysConstant.APPLICATION_LOGIN_COUNT, count);

            //7天免登录，登录信息转成Json字符串后存入Cookie
            if ("1".equals(remember)) {
                loginUser.setPassword(password);

                String jsonStr = JSON.toJSONString(loginUser);
                //编码后存入Cookie
                jsonStr = URLEncoder.encode(jsonStr, "utf-8");
                Cookie loginCookie = new Cookie(SysConstant.COOKIE_LOGIN_USER, jsonStr);

                loginCookie.setMaxAge(7 * 24 * 60 * 60);
                //任何请求都能获得Cookie
                loginCookie.setPath("/");
                response.addCookie(loginCookie);
            }
            response.sendRedirect("/view/common/home.jsp");
        }
    }

    /**
     * @Description: 获取图片验证码
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/4 19:05
     */
    public void getPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        //获取验证码图片
        BufferedImage image = imgCodeUtil.getImage();

        ////获取验证码文本内容
        String code = imgCodeUtil.getText();

        //把图片验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_PIC_CODE_NAME, code);

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

    /**
     * @Description: 登出
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/5 18:40
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //在线人数减一
        ServletContext application = getServletContext();
        Integer count = (Integer) application.getAttribute(SysConstant.APPLICATION_LOGIN_COUNT);
        if (count >= 1) {
            application.setAttribute(SysConstant.APPLICATION_LOGIN_COUNT, --count);
        }

        //销毁session
        HttpSession session = request.getSession();
        session.invalidate();

        //销毁cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        response.sendRedirect("/index.jsp");
    }
}
