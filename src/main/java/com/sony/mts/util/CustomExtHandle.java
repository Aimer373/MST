/**
 * @Title: CustomExtHandle.java
 * @Package: com.sony.mts.util
 * @Description: 检测异常
 * @author: 5109u12412宁誉程
 * @date: 2021/11/10 11:06:47
 * @Company: sony
 * @version: V1.0
 */
package com.sony.mts.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: CustomExtHandle
 * @Description: 监测异常
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/10 11:06:47
 */
@ResponseStatus
public class CustomExtHandle {
	/**
	 * @Title: handleMyException
	 * @Description: 把需要的异常信息向前端页面输出
	 * @param: @param e
	 * @param: @param request
	 * @return: Object
	 */
	@ExceptionHandler(value = MyException.class)
	Object handleMyException(MyException e, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Date date = new Date();
		Timestamp nousedate = new Timestamp(date.getTime());
		// 指定错误跳转页面
		modelAndView.setViewName("error.html");
		modelAndView.addObject("code", e.getCode());
		modelAndView.addObject("msg", e.getMsg());
		modelAndView.addObject("message", e.getMessage());
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.addObject("timestamp", nousedate);
		return modelAndView;
	}

}
