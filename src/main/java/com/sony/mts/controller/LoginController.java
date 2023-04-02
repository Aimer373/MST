/**
 * @Title: LoginController.java
 * @Package: com.sony.mts.controller
 * @Description: 描述该文件做什么
 * @author: 5109u12412宁誉程
 * @date: 2021/11/03 10:19:32
 * @Company: sony
 * @version: V1.0
 * @Copyright: 版权
 */
package com.sony.mts.controller;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.sony.mts.entity.Employee;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.util.CustomExtHandle;
import com.sony.mts.util.MyException;

@Controller
/**
 * @ClassName: LoginController
 * @Description: 登录Controller
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/03 10:19:32
 */
public class LoginController extends CustomExtHandle {
	@Autowired
	EmployeeService employeeService;

	/**
	 * @Title: login
	 * @Description: 登录信息及身份验证
	 * @param: @param empId 员工编号
	 * @param: @param passWd密码
	 * @param: @param model
	 * @param: @param session
	 * @return: String
	 */
	@PostMapping("/login")
	public String login(@RequestParam(value = "empId") String empId, @RequestParam(value = "passWd") String passWd,
			Model model, HttpSession session) {
		log.info(properties.getProperty("startLogin"));
		try {
			// 登录校验
			if (checkLogin(empId, model, passWd)) {
				Employee employee = employeeService.findByPrimaryKey(empId);
				session.setAttribute("employeeEmpId", employee);
				model.addAttribute("employee", employee);
				String posNum = employee.getPosNum();
				// 根据职位返回相应页面（董事长，管理层，员工）
				return checkPosNum(posNum);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("loginFailed"), e);
			model.addAttribute("exceptions", properties.getProperty("loginFailed"));
			throw new MyException(409, properties.getProperty("loginFailed"), e.getMessage());
		}
		return "/index";
	}

	/**
	 * @Title: logout
	 * @Description: 用户退出后的登录界面
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		log.info(properties.getProperty("startLogout"));
		try {
			request.getSession().invalidate();
			log.info(properties.getProperty("logoutSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("logoutFailed"), e);
			throw new MyException(409, properties.getProperty("logoutFailed"), e.getMessage());
		}
		return "/index";
	}

	/**
	 * @Title: toMain
	 * @Description: 跳转到首页
	 * @param: @param request
	 * @param: @param model
	 * @return: String
	 */
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request, Model model) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String posNum = employee.getPosNum();
		model.addAttribute("employee", employee);
		model.addAttribute("posNum", posNum);
		// 根据职位返回相应页面（董事长，管理层，员工）
		return checkPosNum(posNum);
	}

	/**
	 * @Title: checkPassWd
	 * @Description: 登录功能校验
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @param: @param passWd 密码
	 * @return: boolean
	 */
	public boolean checkLogin(String empId, Model model, String passWd) {
		if (StringUtils.isEmptyOrWhitespace(empId) || StringUtils.isEmptyOrWhitespace(passWd)) {
			model.addAttribute("loginMsg", properties.getProperty("empIdOrPassWdIsNull"));
			log.info(properties.getProperty("empIdOrPassWdIsNull"));
			return false;
		}
		Employee employee = employeeService.findByPrimaryKey(empId);
		if (employee == null) {
			model.addAttribute("loginMsg", properties.getProperty("employeeIsNull"));
			log.info(properties.getProperty("employeeIsNull"));
			return false;
		}
		String passWord = employee.getPassWd();
		if (!passWd.equals(passWord)) {
			model.addAttribute("loginMsg", properties.getProperty("passWdIsError"));
			log.info(properties.getProperty("passWdIsError"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkPosNum
	 * @Description: 判断职位级别
	 * @param: @param posNum 职位编号
	 * @param: @return 返回的页面
	 * @return: String
	 */
	public String checkPosNum(String posNum) {
		if ("X1".equals(posNum)) {
			log.info(properties.getProperty("X1LoginSuccess"));
			return "/main/workers";
		}
		if ("A1".equals(posNum)) {
			log.info(properties.getProperty("A1LoginSuccess"));
			return "/main/management";
		}
		log.info(properties.getProperty("loginSuccess"));
		return "/main/manager";
	}
}
