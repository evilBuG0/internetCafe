package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.Grade;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT o FROM Grade o WHERE o.delFlag = 0")
    public List<Grade> findGradeByDelFlag();
}
