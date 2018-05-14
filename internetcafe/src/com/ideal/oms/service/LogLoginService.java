package com.ideal.oms.service;


import com.ideal.oms.entity.LogLogin;
import com.ideal.oms.framework.orm.DynamicSpecifications;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.repository.LogLoginRepository;
import com.ideal.oms.util.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;


@Service
@Transactional
public class LogLoginService {
    private Logger logger = LoggerFactory.getLogger(LogLoginService.class);
    @Resource
    private LogLoginRepository logLoginRepository;

    public Page<LogLogin> findLogLogin(Collection<SearchFilter> filters, Pageable pageable){
        Specification<LogLogin> specification = DynamicSpecifications.bySearchFilter(filters);
        Page<LogLogin> page = logLoginRepository.findAll(specification, pageable);
        return page;
    }


    public LogLogin saveLogLogin(String username, String context){
        LogLogin logLogin = null;
        try {
            logLogin = new LogLogin();
            logLogin.setContent(context);
            logLogin.setCreateUser(username);
            logLogin.setCreateIp(ContextUtils.getIp());
            logLogin.setCreateTime(new Date());
            logLoginRepository.save(logLogin);
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.debug(ex.getMessage());
        }
        return logLogin;
    }
}