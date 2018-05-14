package com.ideal.oms.security.repository;

import com.ideal.oms.entity.security.Menu;
import com.ideal.oms.entity.security.RoleMenu;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {
    @Query("SELECT rm.menu FROM RoleMenu rm WHERE rm.role.id IN (?1) AND rm.menu.type=1")
    public List<Menu> findAllInRole(List<Long> roleIds);

    public List<RoleMenu> findRoleMenusByRoleId(Long roleId);

    @Query("SELECT rm FROM RoleMenu rm WHERE rm.role.id = (?1) AND rm.menu.id = (?2)")
    public RoleMenu findRoleMenuByRoleIdAndMenuId(Long roleId, Long menuId);

    @Query("SELECT rm.menu.code FROM RoleMenu rm WHERE rm.role.id IN (?1) AND rm.menu.type= 2")
    List<String> findButtonsByRoles(List<Long> roles);
}
