package com.lrb.sys.dao;

import com.lrb.sys.entity.Dept;
import com.lrb.utils.DBUtil;
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
}
