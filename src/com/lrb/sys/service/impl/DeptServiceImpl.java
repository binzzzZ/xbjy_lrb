package com.lrb.sys.service.impl;

import com.lrb.sys.dao.DeptDao;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;
import com.lrb.sys.service.DeptService;
import com.lrb.sys.utils.DateUtil;

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

    /**
     * @Description: 查询所有部门
     * @author: lrb
     * @param: [name, page]
     * @return: java.util.List<com.lrb.sys.entity.Dept>
     * @create: 2019/12/4 12:05
     */
    @Override
    public List<Dept> listAll(String name, Page page) {
        return deptDao.listAll(name, page);
    }

    /**
     * @Description: 获取总记录数
     * @author: lrb
     * @param: [name]
     * @return: java.lang.Integer
     * @create: 2019/12/4 12:05
     */
    @Override
    public Integer getCount(String name) {
        return deptDao.getCount(name);
    }

    /**
     * @Description: 通过ID获取部门下的人数
     * @author: lrb
     * @param: [id]
     * @return: java.lang.Integer
     * @create: 2019/12/4 12:05
     */
    @Override
    public Integer getDeptUserCount(Integer id) {
        return deptDao.getDeptUserCount(id);
    }

    /**
     * @Description: 通过ID删除部门
     * @author: lrb
     * @param: [id]
     * @return: void
     * @create: 2019/12/4 12:06
     */
    @Override
    public void deleteById(Integer id) {
        deptDao.deleteById(id);
    }

    /**
     * @Description: 添加一个部门
     * @author: lrb
     * @param: [dept]
     * @return: void
     * @create: 2019/12/4 12:06
     */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(DateUtil.getDateStr());
        deptDao.add(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptDao.getById(id);
    }

    @Override
    public void updateById(Dept dept) {
        deptDao.updateById(dept);
    }
}
