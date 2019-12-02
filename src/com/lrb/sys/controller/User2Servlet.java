package com.lrb.sys.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:30
 * @Description
 */
//@WebServlet("/sys/user/*")
public class User2Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String[] methods = uri.split("/");
        String methodStr = methods[methods.length - 1];

        switch (methodStr) {
            case "list":
                list(request, response);
            case "add":
                add(request, response);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("list");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("add");
    }
}
