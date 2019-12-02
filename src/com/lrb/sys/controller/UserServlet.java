package com.lrb.sys.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:18
 * @Description
 */
@WebServlet("/sys/user/*")
public class UserServlet extends BaseServlet {
    public void list(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("user list");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("user add");
    }
}
