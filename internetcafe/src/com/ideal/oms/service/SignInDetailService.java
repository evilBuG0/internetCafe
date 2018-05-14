package com.ideal.oms.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideal.oms.entity.SignInDetail;
import com.ideal.oms.repository.SignInDetailRepository;

@Service
public class SignInDetailService {
    @Resource
    private SignInDetailRepository signInDtailRepository;

    public Iterable<SignInDetail> saveSignInDetail(List<SignInDetail> sits){
    	return signInDtailRepository.save(sits);
    }
    public List<SignInDetail> findSignInDetailBySignId(Long signId){
    	return signInDtailRepository.findSignInDetailBySignId(signId);
    }
   
}
