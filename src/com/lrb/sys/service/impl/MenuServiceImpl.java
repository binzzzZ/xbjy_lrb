package com.lrb.sys.service.impl;

import com.lrb.sys.dao.MenuDao;
import com.lrb.sys.entity.Menu;
import com.lrb.sys.service.MenuService;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/11/29 17:27
 * @Description
 */
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao = new MenuDao();

    @Override
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
