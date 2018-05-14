package com.ideal.oms.web.controller.system;

import com.ideal.oms.dto.MenuTree;
import com.ideal.oms.entity.Parameter;
import com.ideal.oms.entity.security.Menu;
import com.ideal.oms.entity.security.Role;
import com.ideal.oms.entity.security.RoleMenu;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.security.service.SecurityServiceImp;
import com.ideal.oms.service.ParameterService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(AbstractSystemController.PORTAL_PREFIX)
public class RoleManageController extends AbstractSystemController {
    Logger logger = LoggerFactory.getLogger(RoleManageController.class);

    @Resource
    private SecurityServiceImp securityServiceImp;

    @Resource
    private ParameterService parameterService;

    @RequestMapping("role_manage")
    public void index(HttpServletRequest request, Model model) throws Exception{
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<Role> page = securityServiceImp.searchRole(searchParams, buildPageRequest(request, 15, sort));
        List<Parameter> parameterList = parameterService.findParameterListByType("Identity");
        model.addAttribute("parameterList", parameterList);
        model.addAttribute("page", page);
    }

    @RequestMapping("edit_role_pop")
    public void edit(Model model, @RequestParam(value = "id", required = false) Long id) {
        List<Parameter> parameterList = parameterService.findParameterListByType("Identity");
        model.addAttribute("parameterList", parameterList);
        Role roleInfo = null;
        if (id != null) {
            roleInfo = securityServiceImp.getRole(id);
        }
        model.addAttribute("roleInfo", roleInfo);
    }

    @RequestMapping("save_role")
    @ResponseBody
    public JsonObject save(Model model, @RequestParam(value = "id") Long id,
                                  @RequestParam(required = false) String role_name,
                                  @RequestParam(required = false) Long type,
                                  @RequestParam(required = false) String description) {
        Role role = new Role();
        if (id != null) {
            role = securityServiceImp.getRole(id);
            role.setRoleName(role_name);
            role.setType(new Parameter(type));
            role.setDescription(description);
        } else {
            role.setRoleName(role_name);
            role.setType(new Parameter(type));
            role.setDescription(description);
            role.setCreateDate(new Date());
            role.setCreateUser(securityServiceImp.getLoginUser());
            role.setDelFlag(0);
        }
        securityServiceImp.saveRole(role);
        return JsonObject.success();
    }

    @RequestMapping("delete_role")
    @ResponseBody
    public JsonObject delete(@RequestParam(value = "id") Long[] ids) {
        if(ids != null) {
            securityServiceImp.deleteRole(ids);
        }
        return JsonObject.success();
    }

    @RequestMapping("authority_role_pop")
    public void authority(Model model, @RequestParam(value = "id", required = false) Long id) throws Exception{
        List<Menu> allMenus = securityServiceImp.findAllMenus();
        List<MenuTree> menuTreeList = new ArrayList<MenuTree>();
        for (Menu m: allMenus) {
            RoleMenu roleMenu = securityServiceImp.findRoleMenuByRoleIdAndMenuId(id, m.getId());
            if(null != roleMenu) {
                menuTreeList.add(new MenuTree(m.getId(),  m.getParentMenu()==null?null:m.getParentMenu().getId(), m.getMenuName(), false, true));
            } else {
                menuTreeList.add(new MenuTree(m.getId(),  m.getParentMenu()==null?null:m.getParentMenu().getId(), m.getMenuName(), false, false));
            }
        }
        String menuTreeStr = JSONArray.fromObject(menuTreeList).toString();
        model.addAttribute("menuTree", menuTreeStr);
        model.addAttribute("roleId", id);
    }

    @RequestMapping("save_roleMenu")
    @ResponseBody
    public JsonObject saveRoleMenu(String menuIds, Long roleId) {
        List<RoleMenu> roleMenus = securityServiceImp.findRoleMenusByRoleId(roleId);
        if (roleMenus != null) {
            securityServiceImp.deleteRoleMenus(roleMenus);
        }
        String[] str = menuIds.split(",");
        for (String s : str) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRole(new Role(roleId));
            Long menuId = Long.parseLong(s);
            roleMenu.setMenu(new Menu(menuId));
            securityServiceImp.saveRoleMenu(roleMenu);
        }
        return JsonObject.success();
    }
}
