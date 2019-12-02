package com.lrb.sys.entity;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/2 16:57
 * @Description
 */
public class Page {
    //总记录数
    private Integer count;
    //当前页
    private Integer page;
    //每页显示多少条数据
    private Integer pageSize = 3;
    //总页数
    private Integer pageTotal;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
