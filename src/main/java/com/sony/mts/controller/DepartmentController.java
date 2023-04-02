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
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Position;
import com.sony.mts.service.DepartmentService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.PositionService;
import com.sony.mts.util.Access;
import com.sony.mts.util.CustomExtHandle;
import com.sony.mts.util.Flag;
import com.sony.mts.util.MyException;
/**
 * @ClassName: DepartmentController
 * @Description: 部门Controller
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:20:57
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController extends CustomExtHandle {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PositionService positionService;



	/**
	 * @Title: toAdd
	 * @Description: 部门信息追加页面跳转
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toAddDept")
	public String toAdd(Model model, HttpServletRequest request) {
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		return "/department/edit";
	}

	/**
	 * @Title: departmentInsert
	 * @Description: 部门信息追加
	 * @param: @param department 部门对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/addDept")
	public String departmentInsert(Department department, Model model, HttpServletRequest request) {
		log.info(properties.getProperty("addToDeptStart"));
		try {
			// 增加部门校验
			if (checkAddDept(department, model)) {
				departmentService.insertDepartment(department);
				log.info(properties.getProperty("addToDeptSuccess"));
				// 返回分页后的部门一览页面
				return departmentfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("addToDeptFailed"), e);
			throw new MyException(409, properties.getProperty("addToDeptFailed"), e.getMessage());
		}
		return "/department/edit";
	}

	/**
	 * @Title: departmentDel
	 * @Description: 部门信息删除
	 * @param: @param depNum 部门编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/deptDelete/{depNum}")
	public String departmentDel(@PathVariable(value = "depNum") String depNum, Model model,
			HttpServletRequest request) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("deleteToDeptStart"));
			// 删除部门校验
			if (checkDeleteDept(depNum, model)) {
				departmentService.delDepartmentById(depNum);
				log.info(properties.getProperty("deleteToDeptSuccess"));
			}
			// 返回分页后的部门一览页面
			return departmentfindAll(model, request, 1, 5);
		} catch (Exception e) {
			log.error(properties.getProperty("deleteToDeptFailed"), e);
			throw new MyException(409, properties.getProperty("deleteToDeptFailed"), e.getMessage());
		}
	}

	/**
	 * @Title: toUpdate
	 * @Description: 部门信息更新页面跳转
	 * @param: @param depNum 部门编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toUpdateDept/{depNum}")
	public String toUpdate(@PathVariable(value = "depNum") String depNum, Model model, HttpServletRequest request) {
		Department department = departmentService.findByPrimaryKey(depNum);
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		model.addAttribute("department", department);
		return "/department/edit";
	}

	/**
	 * @Title: departmentUpdate
	 * @Description: 部门信息更新
	 * @param: @param department 部门对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/updateDept")
	public String departmentUpdate(Department department, Model model, HttpServletRequest request) {
		String depNum = department.getDepNum();
		model.addAttribute("depNum", depNum);
		try {
			log.info(properties.getProperty("updateToDeptStart"));
			// 部门修改校验
			if (checkUpadteDept(department, model)) {
				departmentService.updateDepartmentByNo(department);
				log.info(properties.getProperty("updateToDeptSuccess"));
				// 返回分页后的部门一览页面
				return departmentfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("updateToDeptFailed"), e);
			throw new MyException(409, properties.getProperty("updateToDeptFailed"), e.getMessage());
		}
		return "/department/edit";
	}

	/**
	 * @Title: getDepartment
	 * @Description: 员工：所属部门信息取得
	 * @param: @param depNum 部门编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/findDept/{depNum}")
	public String getDepartment(@PathVariable(value = "depNum") String depNum, Model model,
			HttpServletRequest request) {
		Department department = null;
		try {
			// 判断访问是否合法
			if (!new Access().accessWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findDepartmentStart"));
			// 员工查询部门信息校验
			if (checkGetDepartment(depNum, model, request)) {
				department = departmentService.findByPrimaryKey(depNum);
				log.info(properties.getProperty("findDepartmentSuccess"));
				model.addAttribute("department", department);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("findDepartmentFailed"), e);
			throw new MyException(409, properties.getProperty("findDepartmentFailed"), e.getMessage());
		}
		return "/department/find";
	}

	/**
	 * @Title: departmentfindAll
	 * @Description: 所有部门信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/findAll")
	public String departmentfindAll(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findAllDepartmentStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Department> pageInfo = new PageInfo<Department>(departmentService.findAllDepartments());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("findAllDepartmentSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("findAllDepartmentFailed"), e);
			throw new MyException(409, properties.getProperty("findAllDepartmentFailed"), e.getMessage());
		}
		return "/department/department";
	}

	/**
	 * @Title: mgrDepartmentfindAll
	 * @Description: 项目经理所有部门信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/managerFindAll")
	public String mgrDepartmentfindAll(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManager(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("mgrFindAllDepartmentStart"));
			// 上传标记判断职位身份
			model.addAttribute("flag", "flag");
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Department> pageInfo = new PageInfo<Department>(departmentService.findAllDepartments());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("mgrFindAllDepartmentSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("mgrFindAllDepartmentFailed"), e);
			throw new MyException(409, properties.getProperty("mgrFindAllDepartmentFailed"), e.getMessage());
		}
		return "/department/department";
	}

	/**
	 * @Title: getDepartmentLike
	 * @Description: 部门信息模糊查找
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/likeDept")
	public String getDepartmentLike(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		String id = request.getParameter("Id");
		String flag = request.getParameter("flag");
		try {
			new Flag().checkFlag(flag, model);
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("likeFindDepartmentStart"));
			// 模糊查询校验
			if (checkGetDepartmentLike(id, model)) {
				PageHelper.startPage(pageNum, pageSize);
				PageInfo<Department> pageInfo = new PageInfo<Department>(departmentService.findByEmpIdLike(id));
				model.addAttribute("pageInfo", pageInfo);
				log.info(properties.getProperty("likeFindDepartmentSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("likeFindDepartmentFailed"), e);
			throw new MyException(409, properties.getProperty("likeFindDepartmentFailed"), e.getMessage());
		}
		return "/department/department";
	}

	/**
	 * @Title: checkAddDept
	 * @Description: 增加部门校验
	 * @param: @param department 部门对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkAddDept(Department department, Model model) {
		String depNum = department.getDepNum();
		String depName = department.getDepName();
		String chairmanNum = department.getChairmanNum();
		Department dep = departmentService.findByPrimaryKey(depNum);
		if (StringUtils.isEmptyOrWhitespace(depNum) || dep != null) {
			model.addAttribute("msg", properties.getProperty("deptNumIsNullOrdeptExist"));
			log.info(properties.getProperty("deptNumIsNullOrdeptExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(depName) || StringUtils.isEmptyOrWhitespace(chairmanNum)) {
			model.addAttribute("msg", properties.getProperty("deptNameOrChairmanNumIsNull"));
			log.info(properties.getProperty("deptNameOrChairmanNumIsNull"));
			return false;
		}
		Department dept = departmentService.findByDepName(depName);
		if (dept != null) {
			model.addAttribute("msg", properties.getProperty("deptNameExist"));
			log.info(properties.getProperty("deptNameExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkDeleteDept
	 * @Description: 删除校验
	 * @param: @param depNum 部门编号
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkDeleteDept(String depNum, Model model) {
		List<Employee> empList = employeeService.findByDepNum(depNum);
		List<Position> posList = positionService.findByDepNum(depNum);
		if (empList.size() != 0 || posList.size() != 0) {
			model.addAttribute("deleteDept", properties.getProperty("deptHaveEmployee"));
			log.info(properties.getProperty("deptHaveEmployee"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkUpadteDept
	 * @Description: 修改部门校验
	 * @param: @param department 部门对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkUpadteDept(Department department, Model model) {
		String depName = department.getDepName();
		String chairmanNum = department.getChairmanNum();
		if (StringUtils.isEmptyOrWhitespace(depName) || StringUtils.isEmptyOrWhitespace(chairmanNum)) {
			model.addAttribute("updateDept", properties.getProperty("deptmessageIsNull"));
			log.info(properties.getProperty("deptmessageIsNull"));
			return false;
		}
		Department dept = departmentService.findByDepName(depName);
		if (dept != null) {
			model.addAttribute("updateDept", properties.getProperty("deptNameExist"));
			log.info(properties.getProperty("deptNameExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetDepartment
	 * @Description: 获取单个部门校验
	 * @param: @param depNum部门编号
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean checkGetDepartment(String depNum, Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String myDepNum = employee.getDepNum();
		if (StringUtils.isEmptyOrWhitespace(depNum)) {
			model.addAttribute("findMsg", properties.getProperty("deptNumIsNull"));
			log.info(properties.getProperty("deptNumIsNull"));
			return false;
		}
		if (!depNum.equals(myDepNum)) {
			model.addAttribute("findMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetDepartmentLike
	 * @Description: 模糊查询部门校验
	 * @param: @param id 输入的信息
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkGetDepartmentLike(String id, Model model) {
		if (StringUtils.isEmptyOrWhitespace(id)) {
			model.addAttribute("findMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		List<Department> departmentList = departmentService.findByEmpIdLike(id);
		if (departmentList.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("deptIsNull"));
			log.info(properties.getProperty("deptIsNull"));
			return false;
		}
		model.addAttribute("Id", id);
		return true;
	}

}
