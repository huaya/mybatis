package com.maxlong.study.model;

import javax.persistence.*;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/13 15:56
 * 类说明
 */
public class BaseEntity {

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
