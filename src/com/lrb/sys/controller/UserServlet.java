package com.lrb.sys.controller;

import com.lrb.sys.constants.SysConstant;
import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;
import com.lrb.sys.service.impl.UserServiceImpl;
import com.lrb.sys.utils.MDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
     * @Description: 查询用户数据
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
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);
        page.setPageCurrent(pageCurrent);

        List<User> list = userService.list(account, page);

        request.setAttribute("list", list);
        request.setAttribute("account", account);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/view/sys/user/list.jsp").forward(request, response);
    }

    /**
     * @Description: 添加一条用户数据
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 17:28
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(user, map);

        //设置创建人
        user.setCreateBy(super.getLoginUser().getId());
        userService.add(user);
        response.sendRedirect("/sys/user/list");
    }

    /**
     * @Description: 通过ID删除一条用户数据
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 17:33
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
        userService.deleteById(Integer.valueOf(id));
        response.sendRedirect("/sys/user/list");
    }

    /**
     * @Description: 通过ID查询User
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/2 19:45
     */
    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String id = request.getParameter("id");
        if (id == null) {
            return;
        }
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

    /**
     * @Description: 修改密码
     * @author: lrb
     * @param: [request, response]
     * @return: void
     * @create: 2019/12/3 16:59
     */
    public void forget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(SysConstant.SESSION_EMAIL_CODE_NAME);

        if (obj == null || !obj.toString().equals(code)) {
            response.sendRedirect("/view/sys/user/forget.jsp");
            return;
        }

        User user = new User();
        user.setAccount(account);
        user.setPassword(MDUtil.md5(password));
        userService.updatePassword(user);

        response.sendRedirect("/index.jsp");
    }
}
