package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.Grade;
import com.ideal.oms.repository.GradeRepository;

@Service
public class GradeService {
    @Resource
    private GradeRepository gradeRepository;

    public List<Grade> findGradeByDelFlag(){
    	return gradeRepository.findGradeByDelFlag();
    }

   
}
