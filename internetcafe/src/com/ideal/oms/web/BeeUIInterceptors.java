package com.ideal.oms.web;

import com.ideal.oms.security.SecurityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeeUIInterceptors extends UIInterceptors {

    @Resource
    private SecurityService securityService;

    @Override
    public void postHandle0(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (StringUtils.isBlank(request.getHeader("x-requested-with"))) {
            String requestUri = request.getServletPath();
            request.setAttribute("menuList", securityService.buildMenu(requestUri));
        }
    }
}
