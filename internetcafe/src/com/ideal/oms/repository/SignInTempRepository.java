package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.SignInTemp;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface SignInTempRepository extends JpaRepository<SignInTemp, Long> {
	@Query("SELECT o FROM SignInTemp o WHERE o.delFlag = 0 and o.serialNum=?1 and o.createUser.id=?2")
    public List<SignInTemp> findSignInTempBySerialNumAndUserId(String serialNum,Long userId);
	
	
    
}
