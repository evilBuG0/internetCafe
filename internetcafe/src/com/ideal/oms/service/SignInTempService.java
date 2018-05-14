package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.SignInTemp;
import com.ideal.oms.repository.SignInTempRepository;

@Service
public class SignInTempService {
    @Resource
    private SignInTempRepository signInTempRepository;

    public SignInTemp saveSignInTemp(SignInTemp sit){
    	return signInTempRepository.save(sit);
    }
    
    public List<SignInTemp> findSignInTempBySerialNumAndUserId(String serialNum,Long userId){
    	return signInTempRepository.findSignInTempBySerialNumAndUserId(serialNum, userId);
    }
    public void deletSignInTempBySerialNumAndUserId(List<SignInTemp> signInTempList){
    	signInTempRepository.delete(signInTempList);
	}
   
}
