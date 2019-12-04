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
    private Integer pageCurrent;
    //每页显示多少条数据
    private Integer pageSize = 4;
    //总页数
    private Integer pageCount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return this.count % this.pageSize == 0 ? this.count / this.pageSize : (this.count / this.pageSize + 1);
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
