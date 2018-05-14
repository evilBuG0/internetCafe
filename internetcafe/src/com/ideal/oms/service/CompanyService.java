package com.ideal.oms.service;


import com.ideal.oms.entity.Company;
import com.ideal.oms.framework.orm.DynamicSpecifications;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {
    @Resource
    private CompanyRepository companyRepository;

    public Page<Company> searchCompany(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Specification<Company> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<Company> customers = companyRepository.findAll(spec, pageable);
        return customers;
    }

    public List<Company> findCompany() {
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }
}
