package com.lrb.sys.controller;

import com.alibaba.fastjson.JSON;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.service.impl.DeptServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:21
 * @Description
 */
@WebServlet("/sys/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptServiceImpl deptService = new DeptServiceImpl();

    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Dept> list = deptService.list();
        String deptStr = JSON.toJSONString(list);

        PrintWriter out = response.getWriter();
        out.append(deptStr);
    }
}
