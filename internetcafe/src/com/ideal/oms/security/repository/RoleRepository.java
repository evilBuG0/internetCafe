package com.ideal.oms.security.repository;

import com.ideal.oms.entity.security.Role;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT o FROM Role o WHERE o.id IN (?1)")
    public List<Role> findByIds(List<Long> roleIds);

    @Query("SELECT o FROM Role o WHERE o.delFlag = 0")
    public List<Role> findRolesByDelFlag();
}
