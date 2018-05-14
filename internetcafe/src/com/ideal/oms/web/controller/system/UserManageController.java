package com.ideal.oms.web.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
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

import com.ideal.oms.entity.Company;
import com.ideal.oms.entity.security.Role;
import com.ideal.oms.entity.security.User;
import com.ideal.oms.entity.security.UserRole;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.framework.web.message.WebMessageLevel;
import com.ideal.oms.security.service.SecurityServiceImp;
import com.ideal.oms.service.CompanyService;

@Controller
@RequestMapping(AbstractSystemController.PORTAL_PREFIX)
public class UserManageController extends AbstractSystemController {
    Logger logger = LoggerFactory.getLogger(UserManageController.class);

    @Resource
    private SecurityServiceImp securityServiceImp;

    @Resource
    private CompanyService companyService;

    @RequestMapping("user_manage")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Pageable pageable = buildPageRequest(request, 15, new Sort(Sort.Direction.DESC, "id"));
        Page<User> page = securityServiceImp.searchUser(searchParams, pageable);
        model.addAttribute("page", page);
    }

    @RequestMapping("edit_user_pop")
    public void edit(Model model, @RequestParam(value = "id", required = false) Long id) {
        List<Role> roleList = securityServiceImp.findRolesByDelFlag();
        model.addAttribute("roleList", roleList);
        List<Company> companyList = companyService.findCompany();
        model.addAttribute("companyList", companyList);
        User userInfo = null;
        if (id != null) {
            userInfo = securityServiceImp.getUser(id);
        }
        model.addAttribute("userInfo", userInfo);
    }

    @RequestMapping("save_user")
    @ResponseBody
    public JsonObject save(Model model, @RequestParam(value = "id") Long id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) Long roleId) {
        User user = new User();
        UserRole userRole = new UserRole();
        // 编辑
        if (id != null) {
            user = securityServiceImp.getUser(id);
            user.setUsername(username);
            // user.setPassword(password);
            user.setRole(new Role(roleId));
            //user.setCompany(new Company(companyId));
            user.setModifyUser(securityServiceImp.getLoginUser());
            user.setModifyDate(new Date());
            securityServiceImp.saveUser(user);
            //向用户角色表插入对应的记录
            userRole = securityServiceImp.findUserRoleByUser(id);
            userRole.setRole(new Role(roleId));
            securityServiceImp.saveUserRole(userRole);
        }
        // 新增
        else {
            User userInfo = securityServiceImp.findUserByUsername(username);
            if (null != userInfo) {
                return JsonObject.alert("用户名已存在！", WebMessageLevel.WARN);
            }
            user.setPassword(DigestUtils.md5Hex(password));
            user.setUsername(username);
            user.setRole(new Role(roleId));
            //user.setCompany(new Company(companyId));
            user.setDelFlag(0);
            user.setCreateDate(new Date());
            user.setCreateUser(securityServiceImp.getLoginUser());
            user.setModifyUser(securityServiceImp.getLoginUser());
            user.setModifyDate(new Date());
            securityServiceImp.saveUser(user);
            //向用户角色表插入对应的记录
            userRole.setUser(user);
            userRole.setRole(new Role(roleId));
            securityServiceImp.saveUserRole(userRole);
        }
        return JsonObject.success();
    }

    @RequestMapping("delete_user")
    @ResponseBody
    public JsonObject delete(@RequestParam(value = "id") Long[] ids) {
        if(ids != null) {
            securityServiceImp.deleteUser(ids);
        }
        return JsonObject.success();
    }

    @RequestMapping("reset_password_pop")
    public void resetPassword(Model model, @RequestParam(value = "id", required = false) Long id) {
        User userInfo = null;
        if (id != null) {
            userInfo = securityServiceImp.getUser(id);
        }
        model.addAttribute("userInfo", userInfo);
    }

    @RequestMapping("reset_password")
    @ResponseBody
    public JsonObject reset(Model model, @RequestParam(value = "id") Long id,
                            @RequestParam(required = false) String password) {
        User user = securityServiceImp.getUser(id);
        user.setPassword(DigestUtils.md5Hex(password));
        securityServiceImp.saveUser(user);
        return JsonObject.success();
    }
}