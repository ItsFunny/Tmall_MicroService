package com.tmall.server.product.common.model;

import java.util.Date;

public class TmallCategory {
    private Integer categoryId;

    private String categoryName;

    private Integer categoryDisplaySeq;

    private Integer categoryPid;

    private Boolean status;

    private Date createDate;

    private Date updateDate;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryDisplaySeq() {
        return categoryDisplaySeq;
    }

    public void setCategoryDisplaySeq(Integer categoryDisplaySeq) {
        this.categoryDisplaySeq = categoryDisplaySeq;
    }

    public Integer getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(Integer categoryPid) {
        this.categoryPid = categoryPid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}