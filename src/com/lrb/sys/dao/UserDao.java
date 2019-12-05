package com.lrb.sys.dao;

import com.lrb.sys.entity.DateEntity;
import com.lrb.sys.entity.Page;
import com.lrb.sys.entity.User;
import com.lrb.sys.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 16:45
 * @Description
 */
public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<User> list(String account, Page page, DateEntity date) {
        String sql = "select d.name deptName,u.id id,u.account account,u.name name,u.age age,u.sex sex," +
                "u.birth_date birthDate,u.create_time createTime from sys_user u left join sys_dept d on u.dept_id=d" +
                ".id where account like ? and u.create_time between ? and ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + account + "%", date.getBeginDate(),
                date.getEndDate(), (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer getCount(String account, DateEntity date) {
        String sql = "select count(1) from sys_user where account like ? and create_time between ? and ?";
        return template.queryForObject(sql, Integer.class, "%" + account + "%", date.getBeginDate(),
                date.getEndDate());
    }

    public void add(User user) {
        String sql = "insert into sys_user (id,dept_id,account,password,name,age,sex,email,birth_date,create_time," +
                "create_by) values (null,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getDeptId(), user.getAccount(), user.getPassword(), user.getName(), user.getAge(),
                user.getSex(), user.getEmail(), user.getBirthDate(), user.getCreateTime(), user.getCreateBy());
    }

    public void deleteById(Integer id) {
        String sql = "delete from sys_user where id = ?";
        template.update(sql, id);
    }

    public User getById(Integer id) {
        String sql = "select * from sys_user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateById(User user) {
        String sql = "update sys_user set dept_id=?,account=?,name=?,age=?,sex=?,email=?,birth_date=? where id = ?";
        template.update(sql, user.getDeptId(), user.getAccount(), user.getName(), user.getAge(),
                user.getSex(), user.getEmail(), user.getBirthDate(), user.getId());
    }

    public void updatePassword(User user) {
        String sql = "update sys_user set password = ? where account = ?";
        template.update(sql, user.getPassword(), user.getAccount());
    }

    public List<User> checkLogin(User user) {
        String sql = "select * from sys_user where account=? and password=?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), user.getAccount(), user.getPassword());
    }

    public String getBegin() {
        String sql = "select create_time from sys_user order by create_time limit 1";
        return template.queryForObject(sql, String.class);
    }

    public String getEnd() {
        String sql = "select create_time from sys_user order by create_time desc limit 1";
        return template.queryForObject(sql, String.class);
    }
}
