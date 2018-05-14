package com.ideal.oms.entity.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideal.oms.framework.entity.AutoModel;

@Entity
@Table(name = "user")
public class User extends AutoModel implements Serializable{
    private String username;
    private String password;
    private Date createDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "create_user")
    private User createUser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="modify_user")
    private User modifyUser;

    private Date modifyDate;

    private int delFlag;

    @Transient
    private List<String> roleNames = new ArrayList<String>();
    @Transient
    private List<Long> roleIds = new ArrayList<Long>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> roleSet = new HashSet<UserRole>();

    public User() {
        super();
    }

    public User(Long id) {
        super(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

   

    public User getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(User modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<UserRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<UserRole> roleSet) {
        this.roleSet = roleSet;
    }

    public void addRole(long id, String roleName) {
        if (!roleNames.contains(roleName)) {
            this.roleNames.add(roleName);
            this.roleIds.add(id);
        }
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
