package com.ideal.oms.repository;

import com.ideal.oms.entity.Company;
import com.ideal.oms.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT o FROM Company o WHERE o.delFlag = 0")
    public List<Company> findAll();
}
