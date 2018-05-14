package com.ideal.oms.service;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ideal.oms.entity.Student;
import com.ideal.oms.framework.orm.DynamicSpecifications;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.repository.StudentRepository;

@Service
public class StudentService {
    @Resource
    private StudentRepository studentRepository;

    public Page<Student> searchStudent(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<Student> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<Student> student = studentRepository.findAll(spec, pageable);
        return student;
    }
    
    public Student findStudentById(Long id){
    	return studentRepository.findStudentById(id);
    }
    
    public void saveStudent(Student stu){
    	studentRepository.save(stu);
    }
    
    public Student findStudentByStudentName(String studentName){
    	return studentRepository.findStudentByStudentName(studentName);
    }
    public void deleteStudent(Long[] ids) {
        Iterable<Student> stus = studentRepository.findAll(Arrays.asList(ids));
        for(Student student : stus){
        	student.setDelFlag(1);
        }
        studentRepository.save(stus);
    }
    public List<Student> getActualNumberByClasss(Long classsId){
    	return studentRepository.getActualNumberByClasss(classsId);
    }
    
   
}
