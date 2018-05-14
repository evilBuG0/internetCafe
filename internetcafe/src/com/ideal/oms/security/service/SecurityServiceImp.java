package com.ideal.oms.security.service;

import com.ideal.oms.entity.security.*;
import com.ideal.oms.framework.orm.DynamicSpecifications;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.security.SecurityService;
import com.ideal.oms.security.ShiroDbRealm;
import com.ideal.oms.security.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户管理类.
 */
@Service
@Transactional
public class SecurityServiceImp implements SecurityService {
    private static Logger logger = LoggerFactory.getLogger(SecurityServiceImp.class);
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private RoleMenuRepository roleMenuRepository;
    @Resource
    private MenuRepository menuRepository;


    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findOne(id);
        Set<UserRole> roles = user.getRoleSet();
        for (UserRole userRole : roles) {
            Role role = userRole.getRole();
            user.addRole(role.getId(), role.getRoleName());
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        User u = userRepository.save(user);
        return u;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles = (List<Role>) roleRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        return roles;
    }

    @Override
    public Page<Role> searchRole(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<Role> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<Role> roles = roleRepository.findAll(spec, pageable);
        return roles;
    }

    @Override
    public List<Role> getRoleByUser(Long id) {
        List<Role> roles = userRoleRepository.findRoleByUser(id);
        return roles;
    }

    @Override
    public void deleteRole(Long[] ids) {
        Iterable<Role> roles = roleRepository.findAll(Arrays.asList(ids));
        for(Role role : roles){
            role.setDelFlag(1);
        }
        roleRepository.save(roles);
        //roleRepository.deleteInId(Arrays.asList(ids));
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User getLoginUser() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return this.getUser(user.getId());
    }


    @Override
    public List<Menu> buildMenu(String requestUri) {
        User loginUser = this.getLoginUser();
        List<Menu> r = new ArrayList<Menu>();
        List<Menu> root = null;
        if(loginUser.getRoleIds()==null||loginUser.getRoleIds().isEmpty()){
            root = new ArrayList<Menu>();
        }else{
            root = roleMenuRepository.findAllInRole(loginUser.getRoleIds());
        }

        if (root != null) {
            for (Menu item : root) {
                if (item.getParentMenu() == null) {
                    List<Menu> sub = new ArrayList<Menu>();
                    for (Menu item2 : root) {
                        if(item2.getParentMenu()!=null)    {
                            if (item.getId().equals(item2.getParentMenu().getId())) {
                                item2.setRequestUri(requestUri);
                                sub.add(item2);
                            }
                        }
                    }
                    item.setSubMenu(sub);  //最多两级菜单
                    item.setRequestUri(requestUri);
                    r.add(item);
                }
            }
        }
        return r;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password);
    }

    protected void entryptPassword(User user, String password) {
        if (StringUtils.isNotEmpty(password)) {
            //user.setPassword(DigestUtils.md5Hex(password));
            user.setPassword(password);
        }
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuRepository.findAllMenus();
    }

    @Override
    public List<Menu> getMenuByParentId() {
        return menuRepository.getMenuByParentId();
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole findUserRoleByUser(Long userId) {
        return userRoleRepository.findUserRoleByUser(userId);
    }

    @Override
    public List<RoleMenu> findRoleMenusByRoleId(Long RoleId) {
        return roleMenuRepository.findRoleMenusByRoleId(RoleId);
    }

    @Override
    public RoleMenu findRoleMenuByRoleIdAndMenuId(Long roleId, Long menuId) {
        return roleMenuRepository.findRoleMenuByRoleIdAndMenuId(roleId, menuId);
    }

    @Override
    public void deleteRoleMenu(RoleMenu roleMenu) {

    }

    @Override
    public void deleteRoleMenus(List<RoleMenu> roleMenus) {
        roleMenuRepository.delete(roleMenus);
    }

    @Override
    public void saveRoleMenu(RoleMenu roleMenu) {
        roleMenuRepository.save(roleMenu);
    }

    @Override
    public User updateUserPwd(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<Menu> searchMenu(Map<String, Object> searchParams, Pageable pageable) {

        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<Menu> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<Menu> menus = menuRepository.findAll(spec, pageable);
        return menus;
    }

    @Override
    public Menu getMenu(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public String deleteMenu(Long[] ids) {
        Iterable<Menu> menus = menuRepository.findAll(Arrays.asList(ids));
        for(Menu menu : menus){
            if(isParentMenu(menu.getId()))   {
                return "0";   //如果为父菜单  删除失败  返回0
            }
//            menu.setDelFlag(1L);
        }
        menuRepository.save(menus);
        return "1";
    }

    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> selectMenu(){

        return menuRepository.selectMenu();
    }

    @Override
    public boolean isParentMenu(Long id){
        if(menuRepository.findByParentId(id)!=null&&menuRepository.findByParentId(id).size()>0)  //有子级菜单  为父菜单
            return true;
        else
            return false;
    }

    @Override
    public List<Menu> findByParentId(Long id){
        return   menuRepository.findByParentId(id);
    }

    // @Override
    public Page<User> searchUser(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<User> users = userRepository.findAll(spec, pageable);
        return users;
    }


    public List<Role> findRolesByDelFlag() {
        List<Role> roles = roleRepository.findRolesByDelFlag();
        return roles;
    }


    public void deleteUser(Long[] ids) {
        Iterable<User> users = userRepository.findAll(Arrays.asList(ids));
        for(User user : users){
            user.setDelFlag(1);
        }
        userRepository.save(users);
    }
}

