package com.ideal.oms.util;

import com.ideal.oms.entity.security.User;
import com.ideal.oms.security.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

public class ContextUtils {
	private static Logger logger = LoggerFactory.getLogger(ContextUtils.class);

	public static User getUser() {
		try {
			ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils
					.getSubject().getPrincipal();
			User user = null;
			if (shiroUser != null) {
				Long userId = shiroUser.getId();
				user = new User();
				user.setId(userId);
				user.setUsername(shiroUser.getUsername());
			}
			return user;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public static String getIp() {
		String remoteIp = null;
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra)
					.getRequest();
			remoteIp = getIp(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return remoteIp;
	}

	public static String getIp(HttpServletRequest request) {
		try {
			String remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
			if (remoteIp == null || remoteIp.length() == 0
					|| "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("x-forwarded-for");
			}
			if (remoteIp == null || remoteIp.length() == 0
					|| "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("Proxy-Client-IP");
			}
			if (remoteIp == null || remoteIp.length() == 0
					|| "unknown".equalsIgnoreCase(remoteIp)) {
			}
			remoteIp = request.getHeader("WL-Proxy-Client-IP");
			if (remoteIp == null || remoteIp.length() == 0
					|| "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getRemoteAddr();
			}
			// 多级反向代理
			if (null != remoteIp && !"".equals(remoteIp.trim())) {
				StringTokenizer st = new StringTokenizer(remoteIp, ",");
				String ipTmp = "";
				if (st.countTokens() > 1) {
					while (st.hasMoreTokens()) {
						ipTmp = st.nextToken();
						if (ipTmp != null && ipTmp.length() != 0
								&& !"unknown".equalsIgnoreCase(ipTmp)) {
							remoteIp = ipTmp;
							break;
						}
					}
				}
			}

			return remoteIp;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			request = ((ServletRequestAttributes) ra).getRequest();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		return request;
	}
}
