package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.Lessons;
import com.ideal.oms.repository.LessonsRepository;

@Service
public class LessonsService {
    @Resource
    private LessonsRepository lessonsRepository;

    public List<Lessons> findClasssByDelFlag(){
    	return lessonsRepository.findLessonsByDelFlag();
    }
    
    

   
}
