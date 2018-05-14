package com.ideal.oms.security;

import com.ideal.oms.entity.security.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SecurityService {
    /*用户*/
    public User findUserByUsername(String username);

    public User getUser(Long id);

    public User saveUser(User user);

    public List<Role> getAllRole();

    /*角色*/
    public Page<Role> searchRole(Map<String, Object> searchParams, Pageable pageable);

    public List<Role> getRoleByUser(Long id);

    public void deleteRole(Long ids[]);

    public Role getRole(Long id);

    public Role saveRole(Role user);

    public User getLoginUser();

    public List<Menu> buildMenu(String requestUri);

    User findUserByUsernameAndPassword(String username, String password);

    public List<Menu> findAllMenus();

    public List<Menu> getMenuByParentId();

    public UserRole saveUserRole(UserRole userRole);

    public UserRole findUserRoleByUser(Long userId);

    public List<RoleMenu> findRoleMenusByRoleId(Long RoleId);

    public RoleMenu findRoleMenuByRoleIdAndMenuId(Long menuId, Long roleId);

    public void deleteRoleMenu(RoleMenu roleMenu);

    public void deleteRoleMenus(List<RoleMenu> roleMenus);

    public void saveRoleMenu(RoleMenu roleMenu);

    User updateUserPwd(User user);

    public Page<Menu> searchMenu(Map<String, Object> searchParams, Pageable pageable);

    public Menu getMenu(Long id);

    public String deleteMenu(Long ids[]);

    public Menu saveMenu(Menu menu);

    public List<Menu> selectMenu();

    boolean isParentMenu(Long id);

    List<Menu> findByParentId(Long id);
}
