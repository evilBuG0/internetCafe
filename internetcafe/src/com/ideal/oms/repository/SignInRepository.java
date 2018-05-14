package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.SignIn;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface SignInRepository extends JpaRepository<SignIn, Long> {
	@Query("SELECT o FROM SignIn o WHERE o.delFlag = 0")
    public List<SignIn> findAll();
    
    
}
