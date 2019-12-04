package com.lrb.sys.controller;

import com.lrb.sys.constants.SysConstant;
import com.lrb.sys.utils.EmailUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:21
 * @Description
 */
@WebServlet("/sys/email/*")
public class EmailServlet extends BaseServlet {

    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        int code = (int) ((Math.random() + 1) * 1000);

        EmailUtil.sendEmail(email, String.valueOf(code));

        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_EMAIL_CODE_NAME, code);
        session.setMaxInactiveInterval(60);

        PrintWriter out = response.getWriter();
        out.append("200");
    }
}
