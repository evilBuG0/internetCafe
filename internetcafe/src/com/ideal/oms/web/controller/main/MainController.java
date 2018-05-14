package com.ideal.oms.web.controller.main;

import com.ideal.oms.web.controller.UIController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MainController.PORTAL_PREFIX)
public class MainController extends UIController {
    public final static String PORTAL_PREFIX = "/main";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }

    @RequestMapping("home_page")
    public void index() {
    }


}