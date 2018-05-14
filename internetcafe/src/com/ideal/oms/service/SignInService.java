package com.ideal.oms.service;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ideal.oms.entity.SignIn;
import com.ideal.oms.framework.orm.DynamicSpecifications;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.repository.SignInRepository;

@Service
public class SignInService {
    @Resource
    private SignInRepository signInRepository;

    public SignIn saveSignIn(SignIn sit){
    	return signInRepository.save(sit);
    }
    
    public Page<SignIn> searchSignIn(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<SignIn> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<SignIn> signIn = signInRepository.findAll(spec, pageable);
        return signIn;
    }

   
}
