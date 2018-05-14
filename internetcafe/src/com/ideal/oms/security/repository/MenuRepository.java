package com.ideal.oms.security.repository;

import com.ideal.oms.entity.security.Menu;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT a FROM Menu a WHERE a.parentMenu IS NULL AND a.delFlag = 0")
    List<Menu> getMenuByParentId();

    @Query("SELECT a FROM Menu a WHERE a.delFlag = 0")
    List<Menu> findAllMenus();

    @Query("SELECT a FROM Menu a WHERE a.parentMenu IS NOT NULL AND a.type=1 AND a.delFlag = 0")
    List<Menu> selectMenu( );

    @Query("SELECT a FROM Menu a WHERE a.parentMenu.id = (?1) AND a.delFlag = 0" )
    List<Menu> findByParentId( Long parentId);
}
