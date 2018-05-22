/**
 * 
 */
package com.huatek.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huatek.core.contants.SessionConstant;
import com.huatek.core.entity.SysUser;
import com.huatek.core.security.SessionThreadLocal;

/**
 * @ClassName: SessionFilter
 * @FullClassPath: com.lpp.mq.core.filter.SessionFilter
 * @Description: TODO
 * @author: Arno
 * @date: 2017年3月31日 上午9:14:26
 * @version: 1.0
 */

public class SessionFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(SessionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.getRequestURI().contains("/sys/login")
				||httpRequest.getRequestURI().contains("/sys/logout") 
				|| httpRequest.getRequestURI().contains("login.html")
				|| httpRequest.getRequestURI().contains("/static/")
				/*|| httpRequest.getRequestURI().contains(".css")
				|| httpRequest.getRequestURI().contains(".jpg")
				|| httpRequest.getRequestURI().contains(".png")*/
				|| httpRequest.getRequestURI().contains("/api/")) {
		     System.out.println("===============httpRequest.getRequestURI====================="+httpRequest.getRequestURI());
			chain.doFilter(request, response);
			return;
		}
		SysUser sysUser = (SysUser) httpRequest.getSession().getAttribute(SessionConstant.USER_SESSION);
		if (null == sysUser) {
			LOGGER.info("【用户会话失效，跳转到首页.】");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
			return;
		} else {
			SessionThreadLocal.setUser(sysUser);
			/*if (SessionThreadLocal.getUser() == null) {
				LOGGER.info("【当前线程" + Thread.currentThread().getName() + "中用户信息为空，从session中set到ThreadLocal.】");
				SessionThreadLocal.setUser(sysUser);
			}*/
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		SessionThreadLocal.remove();
	}

}
