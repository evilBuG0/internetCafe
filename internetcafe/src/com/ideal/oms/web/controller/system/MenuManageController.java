package com.ideal.oms.web.controller.system;

import com.ideal.oms.entity.security.Menu;
import com.ideal.oms.framework.web.json.JsonObject;
import com.ideal.oms.framework.web.message.WebMessageLevel;
import com.ideal.oms.security.service.SecurityServiceImp;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(AbstractSystemController.PORTAL_PREFIX)
public class MenuManageController extends AbstractSystemController {
    Logger logger = LoggerFactory.getLogger(MenuManageController.class);

    @Resource
    private SecurityServiceImp securityServiceImp;

    @RequestMapping("menu_manage")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = WebUtils.getParametersStartingWith(request, "Q_");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<Menu> page = null;
        page = securityServiceImp.searchMenu(searchParams, buildPageRequest(request, 15, sort));
        model.addAttribute("page", page);
    }

    @RequestMapping("edit_menu_pop")
    public void edit(Model model, @RequestParam(value = "id", required = false) Long id) {
        Menu menuInfo = null;
        if (id != null) {
            menuInfo = securityServiceImp.getMenu(id);
        }
        List<Menu> parentMenus= securityServiceImp.getMenuByParentId();
        List<Menu> selectMenus=securityServiceImp.selectMenu();
        model.addAttribute("parentMenus",parentMenus);
        model.addAttribute("selectMenus",selectMenus);
        model.addAttribute("menuInfo", menuInfo);
    }

    @RequestMapping("delete_menu")
    @ResponseBody
    public JsonObject delete(@RequestParam(value = "id") Long[] ids) {

        if(ids != null) {
            if("1".equals(securityServiceImp.deleteMenu(ids)))
                return JsonObject.success();
        }

        return JsonObject.alert("父级菜单不能被删除！", WebMessageLevel.WARN);
    }

    @RequestMapping("save_menu")
    @ResponseBody
    public JsonObject save(Model model, @RequestParam(value = "id") Long id,
                           @RequestParam(required = false) String menuName,
                           @RequestParam(required = false) Integer type,
                           @RequestParam(required = false) String url,
                           @RequestParam(required = false) String cssClass,
                           @RequestParam(required = false) String code,
                           @RequestParam(required = false) Long parentId) {
        Menu menu = new Menu();
        if (id != null) {
            menu = securityServiceImp.getMenu(id);
            menu.setParentMenu(parentId==null?null:new Menu(parentId));
            menu.setLastUpdateDate(new Date());
            menu.setLastUpdateUser(securityServiceImp.getLoginUser());
            menu.setMenuName(menuName);
            menu.setType(type);
            menu.setUrl(url);
            menu.setCode(code);
            menu.setCssClass(cssClass);
        } else {
            menu.setMenuName(menuName);
            menu.setType(type);
            menu.setUrl(url);
            menu.setCode(code);
            menu.setCssClass(cssClass);
            menu.setParentMenu(parentId==null?null:new Menu(parentId));
            menu.setDelFlag(0L);
            menu.setCreateDate(new Date());
            menu.setCreateUser(securityServiceImp.getLoginUser());
            menu.setLastUpdateDate(new Date());
            menu.setLastUpdateUser(securityServiceImp.getLoginUser());
        }
        securityServiceImp.saveMenu(menu);
        return JsonObject.success();
    }

    @RequestMapping("select_menu")
    @ResponseBody
    public List<Menu> selectMenu(long type) {
        if (type==1) {
            return securityServiceImp.getMenuByParentId();
        } else {
            return securityServiceImp.selectMenu();
        }
    }

    @RequestMapping("load_childMenu_pop")
    public void loadChildMenu(Model model, @RequestParam(value = "id", required = false) Long id) {
        List<Menu> childMenus= securityServiceImp.findByParentId(id) ;
        model.addAttribute("childMenus",childMenus);
    }
}
