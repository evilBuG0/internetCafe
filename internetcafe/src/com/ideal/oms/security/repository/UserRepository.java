package com.ideal.oms.security.repository;

import com.ideal.oms.entity.security.User;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT a FROM User a WHERE 1=1 AND a.username = ?1 AND a.password = ?2 AND a.delFlag = 0")
    User findUserByUsernameAndPassword(String username, String password);
}
