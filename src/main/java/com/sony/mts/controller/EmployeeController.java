package com.sony.mts.controller;

import static com.sony.mts.util.MyUtil.log;
import static com.sony.mts.util.MyUtil.properties;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sony.mts.entity.Department;
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Position;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmpProjectRelalService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.PositionService;
import com.sony.mts.util.Access;
import com.sony.mts.util.CustomExtHandle;
import com.sony.mts.util.Flag;
import com.sony.mts.util.MyException;
/**
 * @ClassName: EmployeeController
 * @Description: 员工Controller
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:21:38
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController extends CustomExtHandle {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmpProjectRelalService eprService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private DepartmentService departmentService;

	/**
	 * @Title: toAdd
	 * @Description: 员工信息追加页面跳转
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toAddEmp")
	public String toAdd(Model model, HttpServletRequest request) {
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		return "/employee/edit";
	}

	/**
	 * @Title: employeeInsert
	 * @Description: 员工信息追加
	 * @param: @param employee 员工对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/addEmp")
	public String employeeInsert(Employee employee, Model model, HttpServletRequest request) {
		log.info(properties.getProperty("addToEmployeeStart"));
		try {
			// 添加员工校验
			if (checkAddEmp(employee, model)) {
				employeeService.insertEmployee(employee);
				log.info(properties.getProperty("addToEmployeeSuccess"));
				// 返回分页后的员工一览
				return findAllEmployees(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("addToEmployeeFailed"), e);
			throw new MyException(409, properties.getProperty("addToEmployeeFailed"), e.getMessage());
		}
		return "/employee/edit";
	}

	/**
	 * @Title: employeeDel
	 * @Description: 员工信息删除
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/empDelete/{empId}")
	public String employeeDel(@PathVariable(value = "empId") String empId, Model model, HttpServletRequest request) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("deleteToEmpStart"));
			// 删除员工校验
			if (checkDeleteEmp(empId, model)) {
				employeeService.delEmployeerById(empId);
				log.info(properties.getProperty("deleteToEmpSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("deleteToEmpFailed"), e);
			throw new MyException(409, properties.getProperty("deleteToEmpFailed"), e.getMessage());
		}
		return findAllEmployees(model, request, 1, 5);
	}

	/**
	 * @Title: toUpdate
	 * @Description: 员工信息更新页面跳转
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toUpdateEmp/{empId}")
	public String toUpdate(@PathVariable(value = "empId") String empId, Model model, HttpServletRequest request) {
		Employee employee = employeeService.findByPrimaryKey(empId);
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		model.addAttribute("employee", employee);
		return "/employee/edit";
	}

	/**
	 * @Title: employeeUpdate
	 * @Description: 员工信息更新
	 * @param: @param employee 员工对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/updateEmp")
	public String employeeUpdate(Employee employee, Model model, HttpServletRequest request) {
		String empId = employee.getEmpId();
		model.addAttribute("empId", empId);
		log.info(properties.getProperty("updateToEmpStart"));
		try {
			// 修改员工校验
			if (checkUpdateEmp(employee, model)) {
				employeeService.updateEmployeeByNo(employee);
				log.info(properties.getProperty("updateToEmpSuccess"));
				// 返回分页后的员工一览页面
				return findAllEmployees(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("updateToEmpFailed"), e);
			throw new MyException(409, properties.getProperty("updateToEmpFailed"), e.getMessage());
		}
		return "/employee/edit";
	}

	/**
	 * @Title: getEmployee
	 * @Description: 员工个人信息取得
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/findEmp/{empId}")
	public String getEmployee(@PathVariable(value = "empId") String empId, Model model, HttpServletRequest request) {
		Employee employee = null;
		try {
			// 判断访问是否合法
			if (!new Access().accessWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findEmployeeStart"));
			// 获取单个员工校验
			if (checkGetEmployee(empId, model, request)) {
				employee = employeeService.findByPrimaryKey(empId);
				log.info(properties.getProperty("findEmployeeSuccess"));
				model.addAttribute("employee", employee);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("findEmployeeFailed"), e);
			throw new MyException(409, properties.getProperty("findEmployeeFailed"), e.getMessage());
		}
		return "/employee/find";
	}

	/**
	 * @Title: findAllEmployees
	 * @Description: 所有员工信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页条数
	 * @return: String
	 */
	@GetMapping("/findAll")
	public String findAllEmployees(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findAllEmployeeStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeService.findEmpList());
			log.info(properties.getProperty("findAllEmployeeSuccess"));
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			log.error(properties.getProperty("findAllEmployeeFailed"), e);
			throw new MyException(409, properties.getProperty("findAllEmployeeFailed"), e.getMessage());
		}
		return "/employee/employee";
	}

	/**
	 * @Title: mgrFindAllEmployees
	 * @Description: 项目经理：所有部门信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/managerFindAll")
	public String mgrFindAllEmployees(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManager(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("mgrFindAllEmployeeStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeService.findEmpList());
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("flag", "flag");
			log.info(properties.getProperty("mgrFindAllEmployeeSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("mgrFindAllEmployeeFailed"), e);
			throw new MyException(409, properties.getProperty("mgrFindAllEmployeeFailed"), e.getMessage());
		}
		return "/employee/employee";
	}

	/**
	 * @Title: getEmployeeLike
	 * @Description: 员工信息模糊查找
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/likeEmp")
	public String getEmployeeLike(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		String id = request.getParameter("Id");
		String flag = request.getParameter("flag");
		try {
			new Flag().checkFlag(flag, model);
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("likeFindEmployeeStart"));
			// 员工模糊查询校验
			if (checkGetEmployeeLike(id, model)) {
				PageHelper.startPage(pageNum, pageSize);
				PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeService.findByEmpIdLike(id));
				model.addAttribute("pageInfo", pageInfo);
				log.info(properties.getProperty("likeFindEmployeeSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("likeFindEmployeeFailed"), e);
			throw new MyException(409, properties.getProperty("likeFindEmployeeFailed"), e.getMessage());
		}
		return "/employee/employee";
	}

	/**
	 * @Title: checkAddEmp
	 * @Description: 增加员工校验
	 * @param: @param employee 员工对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkAddEmp(Employee employee, Model model) {
		String empId = employee.getEmpId();
		String depNum = employee.getDepNum();
		String cardId = employee.getCardId();
		String emailAdr = employee.getEmailAdr();
		String empName = employee.getEmpName();
		String mobileNum = employee.getMobileNum();
		String passWd = employee.getPassWd();
		String posNum = employee.getPosNum();
		String sex = employee.getSex();
		Employee emp = null;
		Department department = null;
		Position position = null;
		Employee empByCardId = null;
		Employee empByMobileNum = null;
		Employee empByEmailAdr = null;
		if (StringUtils.isEmptyOrWhitespace(empId)) {
			model.addAttribute("msg", properties.getProperty("empIdIsNull"));
			log.info(properties.getProperty("empIdIsNull"));
			return false;
		}
		emp = employeeService.findByPrimaryKey(empId);
		if (emp != null) {
			model.addAttribute("msg", properties.getProperty("employeeExist"));
			log.info(properties.getProperty("empNumExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(depNum) || StringUtils.isEmptyOrWhitespace(cardId)
				|| StringUtils.isEmptyOrWhitespace(emailAdr) || StringUtils.isEmptyOrWhitespace(empName)
				|| StringUtils.isEmptyOrWhitespace(mobileNum) || StringUtils.isEmptyOrWhitespace(passWd)
				|| StringUtils.isEmptyOrWhitespace(posNum) || StringUtils.isEmptyOrWhitespace(sex)) {
			model.addAttribute("msg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		department = departmentService.findByPrimaryKey(depNum);
		position = positionService.findByPrimaryKey(posNum);
		if (department == null || position == null) {
			model.addAttribute("msg", properties.getProperty("deptOrPosIsNull"));
			log.info(properties.getProperty("deptOrPosIsNull"));
			return false;
		}
		empByCardId = employeeService.findByCardId(cardId);
		empByMobileNum = employeeService.findByMobileNum(mobileNum);
		empByEmailAdr = employeeService.findByEmailAdr(emailAdr);
		if (empByCardId != null || empByMobileNum != null || empByEmailAdr != null) {
			model.addAttribute("msg", properties.getProperty("mobileNumOrEmailOrCardIdExist"));
			log.info(properties.getProperty("mobileNumOrEmailOrCardIdExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkDeleteEmp
	 * @Description: 删除员工信息校验
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkDeleteEmp(String empId, Model model) {
		List<EmpProjectRela> eprList = eprService.findByEmpId(empId);
		if (eprList.size() > 0) {
			model.addAttribute("deleteEmp", properties.getProperty("employeeHaveEpr"));
			log.info(properties.getProperty("employeeHaveEpr"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkUpdateEmp
	 * @Description: 修改员工信息校验
	 * @param: @param employee 员工对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkUpdateEmp(Employee employee, Model model) {
		String empId = employee.getEmpId();
		String depNum = employee.getDepNum();
		String cardId = employee.getCardId();
		String emailAdr = employee.getEmailAdr();
		String empName = employee.getEmpName();
		String mobileNum = employee.getMobileNum();
		String passWd = employee.getPassWd();
		String posNum = employee.getPosNum();
		String sex = employee.getSex();
		String empCardId = employeeService.findByPrimaryKey(empId).getCardId();
		String empEmailAdr = employeeService.findByPrimaryKey(empId).getEmailAdr();
		String empMobileNum = employeeService.findByPrimaryKey(empId).getMobileNum();
		Employee emp = null;
		Department department = null;
		Position position = null;
		Employee empByCardId = null;
		Employee empByMobileNum = null;
		Employee empByEmailAdr = null;
		if (StringUtils.isEmptyOrWhitespace(empId)) {
			model.addAttribute("updateMsg", properties.getProperty("empIdIsNull"));
			log.info(properties.getProperty("empIdIsNull"));
			return false;
		}
		emp = employeeService.findByPrimaryKey(empId);
		if (emp == null) {
			model.addAttribute("updateMsg", properties.getProperty("employeeIsNull"));
			log.info(properties.getProperty("employeeIsNull"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(cardId) || StringUtils.isEmptyOrWhitespace(depNum)
				|| StringUtils.isEmptyOrWhitespace(emailAdr) || StringUtils.isEmptyOrWhitespace(empName)
				|| StringUtils.isEmptyOrWhitespace(mobileNum) || StringUtils.isEmptyOrWhitespace(passWd)
				|| StringUtils.isEmptyOrWhitespace(posNum) || StringUtils.isEmptyOrWhitespace(sex)) {
			model.addAttribute("updateMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		department = departmentService.findByPrimaryKey(depNum);
		position = positionService.findByPrimaryKey(posNum);
		if (department == null || position == null) {
			model.addAttribute("updateMsg", properties.getProperty("deptOrPosIsNull"));
			log.info(properties.getProperty("deptOrPosIsNull"));
			return false;
		}
		empByCardId = employeeService.findByCardId(cardId);
		empByMobileNum = employeeService.findByMobileNum(mobileNum);
		empByEmailAdr = employeeService.findByEmailAdr(emailAdr);
		if (empMobileNum.equals(mobileNum)) {
			return true;
		}
		if (empEmailAdr.equals(emailAdr)) {
			return true;
		}
		if (empCardId.equals(cardId)) {
			return true;
		}

		if (empByCardId != null || empByMobileNum != null || empByEmailAdr != null) {
			model.addAttribute("updateMsg", properties.getProperty("mobileNumOrEmailOrCardIdExist"));
			log.info(properties.getProperty("mobileNumOrEmailOrCardIdExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetEmployee
	 * @Description: 获取单个员工校验
	 * @param: @param empId员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean checkGetEmployee(String empId, Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String myEmpId = employee.getEmpId();
		if (StringUtils.isEmptyOrWhitespace(empId)) {
			model.addAttribute("findMsg", properties.getProperty("empIdIsNull"));
			log.info(properties.getProperty("empIdIsNull"));
			return false;
		}
		if (!empId.equals(myEmpId)) {
			model.addAttribute("findMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetEmployeeLike
	 * @Description: 员工模糊查询校验
	 * @param: @param id 输入的信息
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkGetEmployeeLike(String id, Model model) {
		if (StringUtils.isEmptyOrWhitespace(id)) {
			model.addAttribute("findMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		List<Employee> employeeList = employeeService.findByEmpIdLike(id);
		if (employeeList.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("empIsNull"));
			log.info(properties.getProperty("empIsNull"));
			return false;
		}
		model.addAttribute("Id", id);
		return true;
	}

}
