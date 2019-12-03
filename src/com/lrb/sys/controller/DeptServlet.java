package com.lrb.sys.controller;

import com.alibaba.fastjson.JSON;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;
import com.lrb.sys.service.impl.DeptServiceImpl;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
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

    public void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        name = StringUtils.isEmpty(name) ? "" : name;

        Page page = new Page();
        //总记录数
        Integer count = deptService.getCount(name);
        page.setCount(count);

        //当前页
        Integer pageCurrent = 1;
        String pageStr = request.getParameter("page");
        if (!StringUtils.isEmpty(pageStr)) {
            pageCurrent = Integer.valueOf(pageStr);
        }
        page.setPage(pageCurrent);

        //总页数
        Integer pageTotal;
        pageTotal = count % 3 == 0 ? count / 3 : count / 3 + 1;
        page.setPageTotal(pageTotal);

        List<Dept> list = deptService.listAll(name, page);

        request.setAttribute("list", list);
        request.setAttribute("name", name);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/sys/dept/list.jsp").forward(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Integer count = deptService.countDeptUser(Integer.valueOf(id));
        if (id == null || count != 0) {
            response.sendRedirect("/sys/dept/listAll");
            return;
        }

        deptService.deleteById(Integer.valueOf(id));
        response.sendRedirect("/sys/dept/listAll");
    }
}
