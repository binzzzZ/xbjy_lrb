package com.lrb.sys.service.impl;

import com.lrb.sys.dao.UserDao;
import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;
import com.lrb.sys.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 16:53
 * @Description
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();

    /**
     * @Description: 查询所有用户数据
     * @author: lrb
     * @param: [account, page]
     * @return: java.util.List<com.lrb.sys.entity.User>
     * @create: 2019/12/2 17:25
     */
    @Override
    public List<User> list(String account, Page page) {
        return userDao.list(account, page);
    }

    /**
     * @Description: 获取总记录数
     * @author: lrb
     * @param: [account]
     * @return: java.lang.Integer
     * @create: 2019/12/2 17:25
     */
    @Override
    public Integer getCount(String account) {
        return userDao.getCount(account);
    }

    /**
     * @Description: 添加一条用户数据
     * @author: lrb
     * @param: [user]
     * @return: void
     * @create: 2019/12/2 17:27
     */
    @Override
    public void add(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        user.setCreateTime(dateStr);
        user.setCreateBy(null);
        userDao.add(user);
    }

    /**
     * @Description: 通过ID删除用户数据
     * @author: lrb
     * @param: [id]
     * @return: void
     * @create: 2019/12/2 17:30
     */
    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    /**
    * @Description: 获取一条记录
    * @author: lrb
    * @param: [id]
    * @return: com.lrb.sys.entity.User
    * @create: 2019/12/2 19:16
    */
    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    /**
     * @Description: 通过ID修改用户数据
     * @author: lrb
     * @param: [user]
     * @return: void
     * @create: 2019/12/2 18:56
     */
    @Override
    public void updateById(User user) {
        userDao.updateById(user);
    }
}
