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
import com.sony.mts.entity.EmpProjectRela;
import com.sony.mts.entity.Employee;
import com.sony.mts.entity.Project;
import com.sony.mts.service.EmpProjectRelalService;
import com.sony.mts.service.EmployeeService;
import com.sony.mts.service.ProjectService;
import com.sony.mts.util.Access;
import com.sony.mts.util.CustomExtHandle;
import com.sony.mts.util.MyException;

/**
 * @ClassName: EmpProRelaController
 * @Description: 任务Controller宁誉程
 * @author: 5109u12412
 * @Company: sony
 * @date: 2021/11/02 11:22:14
 */
@Controller
@RequestMapping("/epr")
public class EmpProRelaController extends CustomExtHandle {

	@Autowired
	EmpProjectRelalService eprService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ProjectService projectService;

	/**
	 * @Title: toAdd
	 * @Description: 任务分配页面跳转
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toAddEpr")
	public String toAdd(Model model, HttpServletRequest request) {
		// 判断访问是否合法
		if (!new Access().accessNotWork(model, request)) {
			return new LoginController().logout(request);
		}
		return "/eprela/edit";
	}

	/**
	 * @Title: empProRelaInsert
	 * @Description: 任务分配
	 * @param: @param record 任务对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/addEpr")
	public String empProRelaInsert(EmpProjectRela record, Model model, HttpServletRequest request) {
		log.info(properties.getProperty("addToEprStart"));
		try {
			// 分配任务校验
			if (checkAddEmp(record, model)) {
				eprService.insertEmpProRela(record);
				log.info(properties.getProperty("addToEprSuccess"));
				// 返回分页后的任务页面
				return empProjectRelafindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("addToEprFailed"), e);
			throw new MyException(409, properties.getProperty("addToEprFailed"), e.getMessage());
		}
		return "/eprela/edit";
	}

	/**
	 * @Title: empProRelaDelete
	 * @Description: 任务删除
	 * @param: @param taskId 任务编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/eprDelete/{taskId}")
	public String empProRelaDelete(@PathVariable(value = "taskId") String taskId, Model model,
			HttpServletRequest request) {
		try {
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("deleteToEprStart"));
			eprService.deleteByPrimaryKey(taskId);
			log.info(properties.getProperty("deleteToEprSuccess"));
			// 返回分页后的任务页面
			return empProjectRelafindAll(model, request, 1, 5);
		} catch (Exception e) {
			log.error(properties.getProperty("deleteToEprFailed"), e);
			throw new MyException(409, properties.getProperty("deleteToEprFailed"), e.getMessage());
		}
	}

	/**
	 * @Title: toUpdate
	 * @Description: 任务更新页面跳转
	 * @param: @param taskId 任务编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toUpdateEpr/{taskId}")
	public String toUpdate(@PathVariable(value = "taskId") String taskId, Model model, HttpServletRequest request) {
		EmpProjectRela empProjectRela = eprService.findByPrimaryKey(taskId);
		// 判断访问是否合法
		if (!new Access().accessNotWork(model, request)) {
			return new LoginController().logout(request);
		}
		model.addAttribute("empProjectRela", empProjectRela);
		return "/eprela/edit";
	}

	/**
	 * @Title: empProRelaUpdate
	 * @Description: 任务更新
	 * @param: @param record 任务对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/updateEpr")
	public String empProRelaUpdate(EmpProjectRela record, Model model, HttpServletRequest request) {
		String taskId = record.getTaskId();
		model.addAttribute("taskId", taskId);
		log.info(properties.getProperty("updateToEprStart"));
		try {
			// 修改任务校验
			if (checkUpdateEmp(record, model)) {
				eprService.updateByPrimaryKey(record);
				log.info(properties.getProperty("updateToEprSuccess"));
				// 返回分页后的任务页面
				return empProjectRelafindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("updateToEprFailed"), e);
			throw new MyException(409, properties.getProperty("updateToEprFailed"), e.getMessage());
		}
		return "/eprela/edit";
	}

	/**
	 * @Title: getEmpProjectRelaByEmpId
	 * @Description: 任务信息取得
	 * @param: @param empId 员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/findEpr/{empId}")
	public String getEmpProjectRelaByEmpId(@PathVariable(value = "empId") String empId, Model model,
			HttpServletRequest request) {
		List<EmpProjectRela> empProRela = null;
		try {
			// 判断访问是否合法
			if (!new Access().accessWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findEprStart"));
			// 获取任务信息校验
			if (checkGetEpr(empId, model, request)) {
				empProRela = eprService.findByEmpId(empId);
				model.addAttribute("empProRela", empProRela);
				log.info(properties.getProperty("findEprSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("findEprFailed"), e);
			throw new MyException(409, properties.getProperty("findEprFailed"), e.getMessage());
		}
		return "/eprela/find";
	}

	/**
	 * @Title: empProjectRelafindAll
	 * @Description: 所有任务信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/findAll")
	public String empProjectRelafindAll(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			log.info(properties.getProperty("findAllEprStart"));
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<EmpProjectRela> pageInfo = new PageInfo<EmpProjectRela>(eprService.findAllEmpProjectRelas());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("findAllEprSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("findAllEprFailed"), e);
			throw new MyException(409, properties.getProperty("findAllEprFailed"), e.getMessage());
		}
		return "/eprela/eprela";
	}

	/**
	 * @Title: getEmpProjectRelaLike
	 * @Description: 任务信息模糊查询
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/likeEpr")
	public String getEmpProjectRelaLike(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		String id = request.getParameter("Id");
		try {
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("likeFindEprStart"));
			// 任务信息模糊查询校验
			if (checkGetEmpProjectRelaLike(id, model)) {
				PageHelper.startPage(pageNum, pageSize);
				PageInfo<EmpProjectRela> pageInfo = new PageInfo<EmpProjectRela>(eprService.findByEmpIdLike(id));
				model.addAttribute("pageInfo", pageInfo);
				log.info(properties.getProperty("likeFindEprSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("likeFindEprFailed"), e);
			throw new MyException(409, properties.getProperty("likeFindEprFailed"), e.getMessage());
		}
		return "/eprela/eprela";
	}

	/**
	 * @Title: checkAddEmp
	 * @Description: 分配任务校验
	 * @param: @param record 任务对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkAddEmp(EmpProjectRela record, Model model) {
		String taskId = record.getTaskId();
		String empId = record.getEmpId();
		String proNum = record.getProNum();
		EmpProjectRela eRela = null;
		Employee employee = null;
		Project project = null;
		eRela = eprService.findByPrimaryKey(taskId);
		if (StringUtils.isEmptyOrWhitespace(taskId) || eRela != null) {
			model.addAttribute("msg", properties.getProperty("taskIdIsNullOrEprExist"));
			log.info(properties.getProperty("taskIdIsNullOrEprExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(empId) || StringUtils.isEmptyOrWhitespace(proNum)) {
			model.addAttribute("msg", properties.getProperty("empIdOrProNumIsNull"));
			log.info(properties.getProperty("empIdOrProNumIsNull"));
			return false;
		}
		employee = employeeService.findByPrimaryKey(empId);
		project = projectService.findByPrimaryKey(proNum);
		if (employee == null || project == null) {
			model.addAttribute("msg", properties.getProperty("empIdOrProNumError"));
			log.info(properties.getProperty("empIdOrProNumError"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkUpdateEmp
	 * @Description: 修改任务校验
	 * @param: @param record 任务对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkUpdateEmp(EmpProjectRela record, Model model) {
		String taskId = record.getTaskId();
		String empId = record.getEmpId();
		String proNum = record.getProNum();
		EmpProjectRela eRela = eprService.findByPrimaryKey(taskId);
		Employee employee = employeeService.findByPrimaryKey(empId);
		Project project = projectService.findByPrimaryKey(proNum);
		if (StringUtils.isEmptyOrWhitespace(taskId) || eRela == null) {
			model.addAttribute("updateEpr", properties.getProperty("taskIdIsNullOrEprIsNull"));
			log.info(properties.getProperty("taskIdIsNullOrEprIsNull"));
			return false;
		}
		if (employee == null || project == null) {
			model.addAttribute("updateEpr", properties.getProperty("empIdOrProNumError"));
			log.info(properties.getProperty("empIdOrProNumError"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetEpr
	 * @Description: 获取任务信息校验
	 * @param: @param empId员工编号
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean checkGetEpr(String empId, Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String myEmpId = employee.getEmpId();
		if (StringUtils.isEmptyOrWhitespace(empId)) {
			model.addAttribute("findMsg", properties.getProperty("empIdIsNull"));
			log.info(properties.getProperty("empIdIsNull"));
			return false;
		}
		List<EmpProjectRela> empProRela = eprService.findByEmpId(empId);
		if (empProRela.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("eprIsNull"));
			log.info(properties.getProperty("eprIsNull"));
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
	 * @Title: checkGetEmpProjectRelaLike
	 * @Description: 任务信息模糊查询校验
	 * @param: @param id 输入的信息
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkGetEmpProjectRelaLike(String id, Model model) {
		if (StringUtils.isEmptyOrWhitespace(id)) {
			model.addAttribute("findMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		List<EmpProjectRela> empProRelaList = eprService.findByEmpIdLike(id);
		if (empProRelaList.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("eprIsNull"));
			log.info(properties.getProperty("eprIsNull"));
			return false;
		}
		model.addAttribute("Id", id);
		return true;
	}

}
