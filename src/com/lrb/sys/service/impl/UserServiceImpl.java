package com.lrb.sys.service.impl;

import com.lrb.sys.dao.UserDao;
import com.lrb.sys.entity.DateEntity;
import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;
import com.lrb.sys.service.UserService;
import com.lrb.sys.utils.DateUtil;
import com.lrb.sys.utils.MDUtil;

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
    public List<User> list(String account, Page page, DateEntity date) {
        return userDao.list(account, page, date);
    }

    /**
     * @Description: 获取总记录数
     * @author: lrb
     * @param: [account]
     * @return: java.lang.Integer
     * @create: 2019/12/2 17:25
     */
    @Override
    public Integer getCount(String account, DateEntity date) {
        return userDao.getCount(account, date);
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
        user.setCreateTime(DateUtil.getDateStr());
        user.setPassword(MDUtil.md5(user.getPassword()));
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
     * @Description: 通过ID查询User
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

    /**
    * @Description: 修改密码
    * @author: lrb
    * @param: [user]
    * @return: void
    * @create: 2019/12/5 15:42
    */
    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }

    /**
    * @Description: 验证账号和密码
    * @author: lrb
    * @param: [user]
    * @return: java.util.List<com.lrb.sys.entity.User>
    * @create: 2019/12/5 15:42
    */
    @Override
    public List<User> checkLogin(User user) {
        return userDao.checkLogin(user);
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
        return userDao.getBegin();
    }

    /**
    * @Description: 获取最新的记录创建时间
    * @author: lrb
    * @param: []
    * @return: java.lang.String
    * @create: 2019/12/5 15:43
    */
    @Override
    public String getEnd() {
        return userDao.getEnd();
    }


}
