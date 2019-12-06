package com.lrb.sys.controller;

import com.alibaba.fastjson.JSON;
import com.lrb.sys.entity.DateEntity;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;
import com.lrb.sys.service.impl.DeptServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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

    /**
    * @Description: 部门信息回显到用户查询
    * @author: lrb
    * @param: [request, response]
    * @return: void
    * @create: 2019/12/6 9:48
    */
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Dept> list = deptService.list();
        String deptStr = JSON.toJSONString(list);

        PrintWriter out = response.getWriter();
        out.append(deptStr);
    }

    /**
     * @Description: 查询所有部门
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/4 9:29
     */
    public void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        name = StringUtils.isEmpty(name) ? "" : name;

        DateEntity date = new DateEntity();
        //开始、结束时间
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");

        if (begin == null || "".equals(begin)) {
            begin = deptService.getBegin();
        }
        if (end == null || "".equals(end)) {
            end = deptService.getEnd();
        }
        date.setBeginDate(begin);
        date.setEndDate(end);

        Page page = new Page();
        //总记录数
        Integer count = deptService.getCount(name, date);
        page.setCount(count);

        //当前页
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);
        page.setPageCurrent(pageCurrent);

        List<Dept> list = deptService.listAll(name, page, date);

        request.setAttribute("list", list);
        request.setAttribute("name", name);
        request.setAttribute("page", page);
        request.setAttribute("date", date);
        request.getRequestDispatcher("/view/sys/dept/list.jsp").forward(request, response);
    }

    /**
     * @Description: 通过ID删除部门
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/4 9:31
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String userCount = request.getParameter("userCount");

        //查询部门下的人数，不为0时不可以删除
        if (id == null || !"".equals(userCount)) {
            response.sendRedirect("/sys/dept/listAll");
            return;
        }

        deptService.deleteById(Integer.valueOf(id));
        response.sendRedirect("/sys/dept/listAll");
    }

    /**
     * @Description: 添加部门
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/4 12:08
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Dept dept = new Dept();
        String name = request.getParameter("name");
        dept.setName(name);
        dept.setCreateBy(super.getLoginUser().getId());

        deptService.add(dept);
        response.sendRedirect("/sys/dept/listAll");
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        Dept dept = deptService.getById(Integer.valueOf(id));

        request.setAttribute("dept", dept);
        request.getRequestDispatcher("/view/sys/dept/update.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Dept dept = new Dept();
        Map<String, String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(dept, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        deptService.updateById(dept);
        response.sendRedirect("/sys/dept/listAll");
    }
}
