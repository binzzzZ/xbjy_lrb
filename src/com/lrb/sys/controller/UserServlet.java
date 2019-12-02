package com.lrb.sys.controller;

import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;
import com.lrb.sys.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 11:18
 * @Description
 */
@WebServlet("/sys/user/*")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * @Description: 查询数据
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 17:27
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        account = StringUtils.isEmpty(account) ? "" : account;

        Page page = new Page();
        //总记录数
        Integer count = userService.getCount(account);
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

        List<User> list = userService.list(account, page);

        request.setAttribute("list", list);
        request.setAttribute("account", account);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/sys/user/list.jsp").forward(request, response);
    }

    /**
     * @Description: 添加数据
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 17:28
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.add(user);
        response.sendRedirect("/sys/user/list");
    }

    /**
     * @Description: 删除数据
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 17:33
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        userService.deleteById(Integer.valueOf(id));
        response.sendRedirect("/sys/user/list");
    }

    /**
    * @Description: 获取一条记录
    * @author: lrb
    * @param: [request, response]
    * @return: void
    * @create: 2019/12/2 19:45
    */
    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getById(Integer.valueOf(id));

        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/sys/user/update.jsp").forward(request, response);
    }

    /**
    * @Description: 修改数据
    * @author: lrb
    * @param: [request, response]
    * @return: void
    * @create: 2019/12/2 19:46
    */
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.updateById(user);
        response.sendRedirect("/sys/user/list");
    }
}
