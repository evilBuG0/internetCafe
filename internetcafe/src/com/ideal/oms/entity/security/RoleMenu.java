package com.ideal.oms.entity.security;

import com.ideal.oms.framework.entity.AutoModel;

import javax.persistence.*;

@Entity
@Table(name = "role_menu")
public class RoleMenu extends AutoModel {
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public RoleMenu() {
        super();
    }

    public RoleMenu(Long id) {
        super(id);
    }

    public RoleMenu(Role role, Menu menu) {
        this.role = role;
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}