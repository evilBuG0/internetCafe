package com.ideal.oms.web.controller.system;

import com.ideal.oms.entity.Parameter;
import com.ideal.oms.framework.orm.SearchFilter;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.framework.web.message.WebMessageLevel;
import com.ideal.oms.security.service.SecurityServiceImp;
import com.ideal.oms.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(AbstractSystemController.PORTAL_PREFIX)
public class ParameterManageController extends AbstractSystemController {
    Logger logger = LoggerFactory.getLogger(ParameterManageController.class);

    @Resource
    private ParameterService parameterService;

    @Resource
    private SecurityServiceImp securityServiceImp;

    @RequestMapping("parameter_manage")
    public void index(Model model, HttpServletRequest request) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = buildPageRequest(request, 15, sort);
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        //filters.put("parent.id", new SearchFilter("parent.id", SearchFilter.Operator.ISNULL));
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Page<Parameter> page = parameterService.searchParameter(filters.values(), pageable);
        model.addAttribute("page_parent", page);
    }

    @RequestMapping("parameter_manage_parent_pop")
    public void queryParentParameter(Model model, HttpServletRequest request) {
        Pageable pageable = buildPageRequest(request);
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        //filters.put("parent.id", new SearchFilter("parent.id", SearchFilter.Operator.ISNULL));
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Page<Parameter> page = parameterService.searchParameter(filters.values(), pageable);
        model.addAttribute("page_parent", page);
    }

    @RequestMapping("parameter_manage_child_pop")
    public void queryChildParameter(Model model, HttpServletRequest request) {
        Pageable pageable = buildPageRequest(request);
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("delFlag", new SearchFilter("delFlag", SearchFilter.Operator.EQ, 0));
        Page<Parameter> page = parameterService.searchParameter(filters.values(), pageable);
        model.addAttribute("page_child", page);
    }

    @RequestMapping("edit_parameter_pop")
    public void edit(Model model, @RequestParam(value = "id", required = false) Long id) {
        Parameter parameterInfo = null;
        if (id != null) {
            parameterInfo = parameterService.findParameterOne(id);
        }
        model.addAttribute("parameterInfo", parameterInfo);
    }

    @RequestMapping("save_parameter")
    @ResponseBody
    public JsonObject save(Model model, @RequestParam(value = "id") Long id,
                           @RequestParam(required = false) Long parentId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String code,
                           @RequestParam(required = false) String type,
                           @RequestParam(required = false) String remark) {
        Parameter parameter = new Parameter();
        if (parentId != null) {
            parameter.setParent(new Parameter(parentId));
        }
        if (id != null) {
            parameter = parameterService.findParameterOne(id);
            parameter.setName(name);
            parameter.setCode(code);
            parameter.setType(type);
            parameter.setRemark(remark);
            parameter.setUpdateUser(securityServiceImp.getLoginUser());
            parameter.setDateLastUpdated(new Date());
        } else {
            Parameter parameterInfo = parameterService.findByCode(code);
            if (null != parameterInfo) {
                return JsonObject.alert("编号已存在！", WebMessageLevel.WARN);
            }
            parameter.setName(name);
            parameter.setCode(code);
            parameter.setType(type);
            parameter.setRemark(remark);
            parameter.setStatus(0L);
            parameter.setDelFlag(0L);
            parameter.setCreateDate(new Date());
            parameter.setCreateUser(securityServiceImp.getLoginUser());
            parameter.setUpdateUser(securityServiceImp.getLoginUser());
            parameter.setDateLastUpdated(new Date());
        }
        parameterService.saveParameter(parameter);
        return JsonObject.success();
    }

    @RequestMapping("delete_parameter_pop")
    @ResponseBody
    public JsonObject delete(@RequestParam(value = "id") Long[] ids) {
        if(ids != null) {
            parameterService.deleteParameter(ids);
        }
        return JsonObject.success();
    }
}
