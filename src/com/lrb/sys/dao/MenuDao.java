package com.lrb.sys.dao;

import com.lrb.sys.entity.Menu;
import com.lrb.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/11/29 17:25
 * @Description
 */
public class MenuDao {
    private JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

    public List<Menu> listAll() {
        String sql = "select * from sys_menu order by order_by";
        return template.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}
