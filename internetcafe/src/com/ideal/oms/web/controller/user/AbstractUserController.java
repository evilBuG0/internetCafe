package com.ideal.oms.web.controller.user;

import com.ideal.oms.web.controller.UIController;

/**
 * Created by dell on 15-3-2.
 */
public class AbstractUserController  extends UIController {
    public final static String PORTAL_PREFIX = "/user";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
