package com.lrb.sys.dao;

import com.lrb.sys.entity.DateEntity;
import com.lrb.sys.entity.Dept;
import com.lrb.sys.entity.Page;
import com.lrb.sys.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 17:43
 * @Description
 */
public class DeptDao {
    private JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<Dept> list() {
        String sql = "select * from sys_dept";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    public List<Dept> listAll(String name, Page page, DateEntity date) {
        String sql = "select d.*,a.counts userCount,u.name createName from sys_dept d left join sys_user u on d.create_by=u.id " +
                "left join (select count(*) counts,dept_id deptId from sys_user group by dept_id) a on d.id=a.deptId " +
                "where d.name like ? and d.create_time between ? and ? order by d.create_time desc limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Dept.class), "%" + name + "%", date.getBeginDate(),
                date.getEndDate(), (page.getPageCurrent() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer getCount(String name, DateEntity date) {
        String sql = "select count(*) from sys_dept where name like ? and create_time between ? and ?";
        return template.queryForObject(sql, Integer.class, "%" + name + "%", date.getBeginDate(),
                date.getEndDate());
    }

    public void deleteById(Integer id) {
        String sql = "delete from sys_dept where id=?";
        template.update(sql, id);
    }

    public void add(Dept dept) {
        String sql = "insert into sys_dept (id,name,create_time,create_by) values (null ,?,?,?)";
        template.update(sql, dept.getName(), dept.getCreateTime(), dept.getCreateBy());
    }

    public Dept getById(Integer id) {
        String sql = "select * from sys_dept where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class), id);
    }

    public void updateById(Dept dept) {
        String sql = "update sys_dept set name=? where id=?";
        template.update(sql, dept.getName(), dept.getId());
    }

    public String getBegin() {
        String sql = "select create_time from sys_dept order by create_time limit 1";
        return template.queryForObject(sql, String.class);
    }

    public String getEnd() {
        String sql = "select create_time from sys_dept order by create_time desc limit 1";
        return template.queryForObject(sql, String.class);
    }
}
