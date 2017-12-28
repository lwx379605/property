package com.lmc.property.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 李敏成
 *
 */
public class BaseInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerMethod han=(HandlerMethod)handler;
		System.out.println(han.getBeanType().getName());
		System.out.println(han.getBean().getClass().getName());
		System.out.println(han.getShortLogMessage());
		System.out.println(han.getMethod().getName());
		System.out.println(han.getReturnType().getClass().getName());
		System.out.println(handler.getClass().getName());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("step 2 : postHandle ");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("step 3 : afterCompletion ");
		
	}
	
}
