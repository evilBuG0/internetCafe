package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.Lessons;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface LessonsRepository extends JpaRepository<Lessons, Long> {
    @Query("SELECT o FROM Lessons o WHERE o.delFlag = 0")
    public List<Lessons> findLessonsByDelFlag();
    
    
}
