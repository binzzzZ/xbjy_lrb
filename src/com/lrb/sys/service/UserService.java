package com.lrb.sys.service;

import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 16:52
 * @Description
 */
public interface UserService {
    public List<User> list(String account, Page page);

    public Integer getCount(String account);

    public void add(User user);

    public void deleteById(Integer id);

    public User getById(Integer id);

    public void updateById(User user);

    public void updatePassword(User user);

    public List<User> checkLogin(User user);
}
