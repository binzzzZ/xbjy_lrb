package com.lrb.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lrb.sys.entity.Menu;
import com.lrb.sys.service.impl.MenuServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/11/29 17:28
 * @Description 菜单控制
 */
@WebServlet("/sys/menu")
public class MenuServlet extends HttpServlet {
    private MenuServiceImpl menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> list = menuService.listAll();

        //父级菜单
        List<Menu> listParent= list.stream().filter(menu -> menu.getType().equals("1")).collect(Collectors.toList());
        //子级菜单
        List<Menu> listSon= list.stream().filter(menu -> menu.getType().equals("2")).collect(Collectors.toList());

        Map<String, List<Menu>> map = new HashMap<>();
        map.put("parent", listParent);
        map.put("son", listSon);

        String mapJsonStr = JSON.toJSONString(map);
//        Map<String, List<Menu>> map2 = JSON.parseObject(mapJsonStr, new TypeReference<Map<String, List<Menu>>>() {});

        PrintWriter out = response.getWriter();
        out.append(mapJsonStr);
    }
}
