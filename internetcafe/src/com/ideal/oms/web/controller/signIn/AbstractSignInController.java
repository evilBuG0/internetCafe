package com.ideal.oms.web.controller.signIn;

import com.ideal.oms.web.controller.UIController;

/**
 * Created by dell on 15-3-2.
 */
public class AbstractSignInController extends UIController {
    public final static String PORTAL_PREFIX = "/signIn";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
