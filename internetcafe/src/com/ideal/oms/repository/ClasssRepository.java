package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.Classs;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface ClasssRepository extends JpaRepository<Classs, Long> {
    @Query("SELECT o FROM Classs o WHERE o.delFlag = 0")
    public List<Classs> findClasssByDelFlag();
    
    @Query("SELECT o FROM Classs o WHERE o.delFlag = 0 and o.grade.id=?")
    public List<Classs> findClasssByGradeId(Long id);
}
