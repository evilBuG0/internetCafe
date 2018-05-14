package com.ideal.oms.entity;

import com.ideal.oms.framework.entity.AutoModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "log_visitor")
public class LogVisitor extends AutoModel implements Serializable {
    private String url;
    private String parameter;
    private String createUser;
    private String createIp;
    private Date createTime;

    // Constructor
    public LogVisitor(){
    }
    public LogVisitor(Long id){
        this.setId(id);
    }

    // Setter and Getter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }
}
