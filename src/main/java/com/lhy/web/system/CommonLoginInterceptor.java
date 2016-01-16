package com.lhy.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lhy.modules.system.domain.CommonUser;
import com.lhy.web.WebConstants;


public class CommonLoginInterceptor implements HandlerInterceptor {

	private String indexURL;

	  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception
	  {
	  }

	  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
	    throws Exception
	  {
	  }

	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception
	  {
		  	String type = request.getHeader("X-Requested-With");
		  	CommonUser loginUser = (CommonUser)request.getSession().getAttribute(WebConstants.LOGIN_USER_KEY);
			if (loginUser!=null){
				return true;
			}
			if ("XMLHttpRequest".equalsIgnoreCase(type)) {// AJAX REQUEST PROCES
				response.addHeader("sessionstatus", "timeout");
			}else{
				response.sendRedirect(request.getContextPath()+this.indexURL);
			}
			return false;
	  }

	  public void setIndexURL(String indexURL) {
	    this.indexURL = indexURL;
	  }
	
}
