 /*package com.cug.lab.filter;

import com.cug.lab.Constants;
import com.cug.lab.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class SysUserFilter implements Filter{

    @Autowired
    private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String username = (String) httpServletRequest.getAttribute("loginUser");
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
	}
    
    
    
   @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) request.getAttribute("loginUser");
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
*/