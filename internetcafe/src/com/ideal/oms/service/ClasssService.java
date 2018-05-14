package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.Classs;
import com.ideal.oms.repository.ClasssRepository;

@Service
public class ClasssService {
    @Resource
    private ClasssRepository classsRepository;

    public List<Classs> findClasssByDelFlag(){
    	return classsRepository.findClasssByDelFlag();
    }
    
    public List<Classs> findClasssByGrade(Long id){
    	return classsRepository.findClasssByGradeId(id);
    }

   
}
