package com.ideal.oms.framework.component;

//import org.apache.catalina.connector.Request;
//import org.apache.catalina.connector.RequestFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: ;liuhanlong Data: 14-5-15 Time: 上午10:29
 */
public class ContentTypeFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(ContentTypeFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// if (request instanceof org.apache.catalina.connector.RequestFacade) {
		// org.apache.catalina.connector.RequestFacade requestFacade =
		// (RequestFacade) request;
		// Class<?> requestFacadeClass = requestFacade.getClass();
		// try {
		// Field requestField = requestFacadeClass.getDeclaredField("request");
		// if (requestField != null) {
		// requestField.setAccessible(true);
		// org.apache.catalina.connector.Request realRequest = (Request)
		// requestField.get(requestFacade);
		// realRequest.getCoyoteRequest().getParameters().setQueryStringEncoding(request.getCharacterEncoding());
		// requestField.setAccessible(false);
		// }
		// } catch (NoSuchFieldException e) {
		// logger.error(e.toString(), e);
		// } catch (IllegalAccessException e) {
		// logger.error(e.toString(), e);
		// }
		// }
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
