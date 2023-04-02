package com.sony.mts.config;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置中心
 */
@Configuration

public class MyMvcConfig implements WebMvcConfigurer {

	/**
	 * @Title: addViewControllers
	 * @Description: 配置转发器
	 * @param: @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 可以将制定路径转发到别的页面 / 默认转发到 index页面
		registry.addViewController("/").setViewName("index");
		// index.html 默认也转发到 index页面
		registry.addViewController("/index.html").setViewName("index");
	}

	/**
	 * @Title: addInterceptors
	 * @Description: 自定义拦截器
	 * @param: @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info(properties.getProperty("InterceptorRegistration"));
		// 拦截所有界面的同时放行静态资源以及login和logout
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(Arrays
				.asList("/static/**", "/index.html", "/login", "/", "/**/*.css", "/**/*.js", "/**/*.img"));
	}

}
