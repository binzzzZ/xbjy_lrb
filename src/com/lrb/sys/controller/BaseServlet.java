package com.lrb.sys.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:10
 * @Description
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String[] methods = uri.split("/");
        String methodStr = methods[methods.length - 1];

        //this
        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            Method method = aClass.getDeclaredMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);

            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
