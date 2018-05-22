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

/**
 * @ClassName: EncodingFilter
 * @FullClassPath: com.frame.core.filter.EncodingFilter
 * @Description: 设置请求字符编码
 * @author: Arno
 * @date: Sep 21, 2017 8:06:58 PM
 * @version: 1.0
 */

public class EncodingFilter implements Filter {

	// 存储编码格式信息
	private String encoding = null;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (this.encoding != null && !this.encoding.equals("")) {
			request.setCharacterEncoding(this.encoding);
			response.setCharacterEncoding(this.encoding);
		} else {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		// 获取在web.xml文件中配置了的编码格式的信息
		this.encoding = config.getInitParameter("encoding");
	}
}
