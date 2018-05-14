package com.ideal.oms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideal.oms.framework.entity.AutoModel;
import com.ideal.oms.entity.security.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="parameter")
public class Parameter extends AutoModel {
    private String code;
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Parameter parent;

    private String remark;
    private Long status;
    private String type;

    private Date createDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_user")
    @JsonIgnore
    private User createUser;

    private Date dateLastUpdated;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="person_last_updated")
    @JsonIgnore
    private User updateUser;

    private Long delFlag;

    public Parameter() {
    }

    public Parameter(Long id) {
        super(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter getParent() {
        return parent;
    }

    public void setParent(Parameter parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "date_last_updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public Long getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }
}
