package com.lrb.sys.service.impl;

import com.lrb.sys.dao.DeptDao;
import com.lrb.sys.entity.DateEntity;
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
    public List<Dept> listAll(String name, Page page, DateEntity date) {
        return deptDao.listAll(name, page, date);
    }

    /**
     * @Description: 获取总记录数
     * @author: lrb
     * @param: [name]
     * @return: java.lang.Integer
     * @create: 2019/12/4 12:05
     */
    @Override
    public Integer getCount(String name, DateEntity date) {
        return deptDao.getCount(name, date);
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

    /**
    * @Description: 通过ID查询部门
    * @author: lrb
    * @param: [id]
    * @return: com.lrb.sys.entity.Dept
    * @create: 2019/12/5 15:48
    */
    @Override
    public Dept getById(Integer id) {
        return deptDao.getById(id);
    }

    /**
    * @Description: 修改部门
    * @author: lrb
    * @param: [dept]
    * @return: void
    * @create: 2019/12/5 15:49
    */
    @Override
    public void updateById(Dept dept) {
        deptDao.updateById(dept);
    }

    /**
     * @Description: 获取最早的记录创建时间
     * @author: lrb
     * @param: []
     * @return: java.lang.String
     * @create: 2019/12/5 15:42
     */
    @Override
    public String getBegin() {
        return deptDao.getBegin();
    }

    /**
     * @Description: 获取最新的记录创建时间
     * @author: lrb
     * @param: []
     * @return: java.lang.String
     * @create: 2019/12/5 15:42
     */
    @Override
    public String getEnd() {
        return deptDao.getEnd();
    }
}
