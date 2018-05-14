package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.SignInDetail;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface SignInDetailRepository extends JpaRepository<SignInDetail, Long> {
	@Query("SELECT o FROM SignInDetail o WHERE o.delFlag = 0 and o.signId.id=?1")
    public List<SignInDetail> findSignInDetailBySignId(Long signId);
    
    
}
