package com.lrb.sys.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:21
 * @Description
 */
@WebServlet("/sys/dept/*")
public class DeptServlet extends BaseServlet{
    public void list(HttpServletRequest request, HttpServletResponse response){
        System.out.println("dept list");
    }
}
