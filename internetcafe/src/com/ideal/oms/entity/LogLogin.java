package com.ideal.oms.entity;

import com.ideal.oms.framework.entity.AutoModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "log_login")
public class LogLogin extends AutoModel implements Serializable {
    private String content;
    private String createIp;
    private String createUser;
    private Date createTime;

    // Constructor
    public LogLogin(){
    }
    public LogLogin(Long id){
        this.setId(id);
    }

    // Setter and Getter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
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
}
