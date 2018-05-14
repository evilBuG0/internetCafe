package com.ideal.oms.web.controller.log;

import com.ideal.oms.web.controller.UIController;

public class AbstractLogController extends UIController {
    public final static String PORTAL_PREFIX = "/log";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
