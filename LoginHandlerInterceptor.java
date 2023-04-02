package com.sony.mts.config;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sony.mts.entity.Employee;


public class LoginHandlerInterceptor implements HandlerInterceptor {

	/**
	 * @Title: preHandle
	 * @Description: 登录拦截器
	 * @param: @param request
	 * @param: @param response
	 * @param: @param handler
	 * @param: @throws Exception
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		if (null == employee) {
			log.info(properties.getProperty("noLogin"));
			request.setAttribute("loginMsg", properties.getProperty("noLogin"));
			request.getRequestDispatcher("/index.html").forward(request, response);
			return false;
		}
			return true;		
	}
}
