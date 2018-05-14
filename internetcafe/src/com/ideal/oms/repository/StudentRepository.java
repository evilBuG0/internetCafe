package com.ideal.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideal.oms.entity.Student;
import com.ideal.oms.framework.orm.JpaRepository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT o FROM Student o WHERE o.delFlag = 0")
    public List<Student> findAll();
    
    @Query("SELECT o FROM Student o WHERE o.delFlag = 0 and o.id=?1")
    public Student findStudentById(Long id);
    
    @Query("SELECT o FROM Student o WHERE o.delFlag = 0 and o.studentName=?1")
    public Student findStudentByStudentName(String studentName);
    
    @Query("SELECT o FROM Student o WHERE o.delFlag = 0 and o.classsId.id=?1")
    public List<Student> getActualNumberByClasss(Long classsId);
    
    
}
