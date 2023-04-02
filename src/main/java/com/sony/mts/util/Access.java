/**
 * @Title: Access.java
 * @Package: com.sony.mts.util
 * @Description: 访问权限
 * @author: 5109u12412宁誉程
 * @date: 2021/11/15 14:23:21
 * @Company: sony
 * @version: V1.0
 */
package com.sony.mts.util;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sony.mts.entity.Employee;

/**
 * @ClassName: Access
 * @Description: 访问权限工具类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/15 14:23:21
 */
public class Access {

	/**
	 * @Title: accessManagement
	 * @Description: 董事长以外的url访问全部认为非法
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean accessManagement(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String posNum = employee.getPosNum();
		model.addAttribute("loginMsg", properties.getProperty("IllegalAccess"));
		if (!posNum.equals("A1")) {
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: accessManager
	 * @Description: 董事长或普通员工以外的url访问全部为合法
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean accessManager(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String posNum = employee.getPosNum();
		if (posNum.equals("A1") || posNum.equals("X1")) {
			model.addAttribute("loginMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: accessWork
	 * @Description: 普通员工以外的url访问全部认为非法
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean accessWork(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String posNum = employee.getPosNum();
		if (!posNum.equals("X1")) {
			model.addAttribute("loginMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: accessWork
	 * @Description: 普通员工以外的url访问全部认为合法
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean accessNotWork(Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String posNum = employee.getPosNum();
		if (posNum.equals("X1")) {
			model.addAttribute("loginMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

}
