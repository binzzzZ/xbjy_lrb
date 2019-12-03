package com.lrb.sys.service.impl;

import com.lrb.sys.dao.DeptDao;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;
import com.lrb.sys.service.DeptService;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 17:46
 * @Description
 */
public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDao();

    @Override
    public List<Dept> list() {
        return deptDao.list();
    }

    @Override
    public List<Dept> listAll(String name, Page page) {
        return deptDao.listAll(name, page);
    }

    @Override
    public Integer getCount(String name) {
        return deptDao.getCount(name);
    }

    @Override
    public Integer countDeptUser(Integer id) {
        return deptDao.countDeptUser(id);
    }

    @Override
    public void deleteById(Integer id) {
        deptDao.deleteById(id);
    }
}
