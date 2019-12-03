package com.lrb.sys.service;

import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 17:46
 * @Description
 */
public interface DeptService {
    public List<Dept> list();

    public List<Dept> listAll(String name, Page page);

    public Integer getCount(String name);

    public Integer countDeptUser(Integer id);

    public void deleteById(Integer id);
}
