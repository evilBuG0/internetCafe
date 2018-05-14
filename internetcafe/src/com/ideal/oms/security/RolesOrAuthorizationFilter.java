package com.ideal.oms.security;

import com.ideal.oms.service.LogVisitorService;
import com.ideal.oms.util.ContextUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RolesOrAuthorizationFilter extends AuthorizationFilter {

    @Resource
    private LogVisitorService logVisitorService;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // url
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String url = httpServletRequest.getRequestURI();
        Map<String,String> parameterMap = new HashMap<String, String>();
        Set<String> set = request.getParameterMap().keySet();
        for(String key : set){
            parameterMap.put(key, Arrays.toString(request.getParameterValues(key)));
        }
        logVisitorService.saveLogVisitor(url, parameterMap.toString(), ContextUtils.getIp(httpServletRequest));
        // role
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String role : rolesArray) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
