package com.lrb.sys.service.impl;

import com.lrb.sys.dao.DeptDao;
import com.lrb.sys.entity.Dept;
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
}
