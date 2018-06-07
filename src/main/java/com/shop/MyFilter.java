package com.shop;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
/**
 * 跨域访问过滤器，解决无Access-Control-Allow-Origin头问题
 * @author Administrator
 *
 */
@WebFilter(filterName = "crossDomainAccessFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("跨域访问过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//String origin = servletRequest.getRemoteHost() + ":" + servletRequest.getRemotePort();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		chain.doFilter(servletRequest, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("跨域访问过滤器初始化");
	}

}
