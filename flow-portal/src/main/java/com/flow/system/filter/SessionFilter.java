package com.flow.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flow.pub.common.PubLog;
import com.flow.system.bean.UserInfo;

public class SessionFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	 
	        HttpSession session = httpServletRequest.getSession();
	        if (httpServletRequest.getServletPath().indexOf("login") > 0) {
	            chain.doFilter(request, response);
	        } else {
	            UserInfo user = (UserInfo)session.getAttribute("userInfo");
	            if (user != null) {
	                chain.doFilter(request, response);
	            } else {
	                PubLog.info("登录已失效，重新登录！");
	                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/portal/login.jsp");
	            }
	        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
