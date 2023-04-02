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
 * @ClassName: PositionController
 * @Description: 职位Controller
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:22:49
 */
@Controller
@RequestMapping("/pos")
public class PositionController extends CustomExtHandle {

	@Autowired
	private PositionService positionService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	/**
	 * @Title: toAdd
	 * @Description: 职位信息追加页面跳转
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toAddPos")
	public String toAdd(Model model, HttpServletRequest request) {
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		return "/position/edit";
	}

	/**
	 * @Title: positionInsert
	 * @Description: 职位信息追加
	 * @param: @param position 职位对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/addPos")
	public String positionInsert(Position position, Model model, HttpServletRequest request) {
		log.info(properties.getProperty("addToPosStart"));
		try {
			// 增加职位校验
			if (checkAddPos(position, model)) {
				positionService.insertPosition(position);
				log.info(properties.getProperty("addToPosSuccess"));
				// 返回分页后的职位一览页面
				return positionfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("addToPosFailed"), e);
			throw new MyException(409, properties.getProperty("addToPosFailed"), e.getMessage());
		}
		return "/position/edit";
	}

	/**
	 * @Title: positionDel
	 * @Description: 职位信息删除
	 * @param: @param posNum 职位编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/posDelete/{posNum}")
	public String positionDel(@PathVariable(value = "posNum") String posNum, Model model, HttpServletRequest request) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("deleteToPosStart"));
			// 删除职位校验
			if (checkDeletePos(posNum, model)) {
				positionService.delPositionById(posNum);
				log.info(properties.getProperty("deleteToPosSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("deleteToPosFailed"), e);
			throw new MyException(409, properties.getProperty("deleteToPosFailed"), e.getMessage());
		}
		return positionfindAll(model, request, 1, 5);
	}

	/**
	 * @Title: toUpdate
	 * @Description: 职位信息更新页面跳转
	 * @param: @param posNum 职位编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toUpdatePos/{posNum}")
	public String toUpdate(@PathVariable(value = "posNum") String posNum, Model model, HttpServletRequest request) {
		Position position = positionService.findByPrimaryKey(posNum);
		// 判断访问是否合法
		if (!new Access().accessManagement(model, request)) {
			return new LoginController().logout(request);
		}
		model.addAttribute("position", position);
		return "/position/edit";
	}

	/**
	 * @Title: positionUpdate
	 * @Description: 职位信息更新
	 * @param: @param position 职位对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/updatePos")
	public String positionUpdate(Position position, Model model, HttpServletRequest request) {
		String posNum = position.getPosNum();
		model.addAttribute("posNum", posNum);
		log.info(properties.getProperty("updateToPosStart"));
		try {
			// 修改职位校验
			if (checkUpdatePos(position, model)) {
				positionService.updatePositionByNo(position);
				log.info(properties.getProperty("updateToPosSuccess"));
				// 返回分页后的职位一览界面
				return positionfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("updateToPosFailed"), e);
			throw new MyException(409, properties.getProperty("updateToPosFailed"), e.getMessage());
		}
		return "/position/edit";
	}

	/**
	 * @Title: getPosition
	 * @Description: 员工个人职位信息取得
	 * @param: @param posNum 职位编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/findPos/{posNum}")
	public String getPosition(@PathVariable(value = "posNum") String posNum, Model model, HttpServletRequest request) {
		Position position = null;
		try {
			// 判断访问是否合法
			if (!new Access().accessWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findPosStart"));
			// 员工个人职位查询校验
			if (checkGetPosition(posNum, model, request)) {
				position = positionService.findByPrimaryKey(posNum);
				model.addAttribute("position", position);
				log.info(properties.getProperty("findPosSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("findPosFailed"), e);
			throw new MyException(409, properties.getProperty("findPosFailed"), e.getMessage());
		}
		return "/position/find";
	}

	/**
	 * @Title: positionfindAll
	 * @Description: 所有职位信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/findAll")
	public String positionfindAll(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManagement(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findAllPosStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Position> pageInfo = new PageInfo<Position>(positionService.findAllPositions());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("findAllPosSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("findAllPosFailed"), e);
			throw new MyException(409, properties.getProperty("findAllPosFailed"), e.getMessage());
		}
		return "/position/position";
	}

	/**
	 * @Title: mgrPositionfindAll
	 * @Description: 项目经理：所有职位信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/managerFindAll")
	public String mgrPositionfindAll(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessManager(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("mgrFindAllPositionStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Position> pageInfo = new PageInfo<Position>(positionService.findAllPositions());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("mgrFindAllPositionSuccess"));
			model.addAttribute("flag", "flag");
		} catch (Exception e) {
			log.error(properties.getProperty("mgrFindAllPositionFailed"), e);
			throw new MyException(409, properties.getProperty("mgrFindAllPositionFailed"), e.getMessage());
		}
		return "/position/position";
	}

	/**
	 * @Title: getPositionLike
	 * @Description: 职位信息模糊查询
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/likePos")
	public String getPositionLike(Model model, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
		String id = request.getParameter("Id");
		String flag = request.getParameter("flag");
		try {
			new Flag().checkFlag(flag, model);
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("likeFindPosStart"));
			// 职位模糊查询校验
			if (checkGetPositionLike(id, model)) {
				PageHelper.startPage(pageNum, pageSize);
				PageInfo<Position> pageInfo = new PageInfo<Position>(positionService.findByEmpIdLike(id));
				model.addAttribute("pageInfo", pageInfo);
				log.info(properties.getProperty("likeFindPosSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("likeFindPosFailed"), e);
			throw new MyException(409, properties.getProperty("likeFindPosFailed"), e.getMessage());
		}
		return "/position/position";
	}

	/**
	 * @Title: checkAddPos
	 * @Description: 增加职位校验
	 * @param: @param position 职位对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkAddPos(Position position, Model model) {
		String posNum = position.getPosNum();
		String posName = position.getPosName();
		String depNum = position.getDepNum();
		Position pos = null;
		Department department = null;
		pos = positionService.findByPrimaryKey(posNum);
		if (StringUtils.isEmptyOrWhitespace(posNum) || pos != null) {
			model.addAttribute("msg", properties.getProperty("posNumIsNullOrPosExist"));
			log.info(properties.getProperty("posNumIsNullOrPosExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(posName) || StringUtils.isEmptyOrWhitespace(depNum)) {
			model.addAttribute("msg", properties.getProperty("posNameIsNullOrDeptNumIsNull"));
			log.info(properties.getProperty("posNameIsNullOrDeptNumIsNull"));
			return false;
		}
		department = departmentService.findByPrimaryKey(depNum);
		if (department == null) {
			model.addAttribute("msg", properties.getProperty("deptIsNull"));
			log.info(properties.getProperty("deptIsNull"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkDeletePos
	 * @Description: 删除职位校验
	 * @param: @param posNum 职位编号
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkDeletePos(String posNum, Model model) {
		List<Employee> empList = employeeService.findByPosNum(posNum);
		if (empList.size() != 0) {
			model.addAttribute("deletePos", properties.getProperty("positionHaveEmp"));
			log.info(properties.getProperty("positionHaveEmp"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkUpdatePos
	 * @Description: 修改职位校验
	 * @param: @param position 职位对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkUpdatePos(Position position, Model model) {
		String posNum = position.getPosNum();
		String posName = position.getPosName();
		String depNum = position.getDepNum();
		Position pos = positionService.findByPrimaryKey(posNum);
		Department department = null;
		if (StringUtils.isEmptyOrWhitespace(posNum) || pos == null) {
			model.addAttribute("updatePos", properties.getProperty("posNumIsNullOrPosIsNull"));
			log.info(properties.getProperty("posNumIsNullOrPosIsNull"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(depNum) || StringUtils.isEmptyOrWhitespace(posName)) {
			model.addAttribute("updatePos", properties.getProperty("posNameIsNullOrDeptNumIsNull"));
			log.info(properties.getProperty("posNameIsNullOrDeptNumIsNull"));
			return false;
		}
		department = departmentService.findByPrimaryKey(depNum);
		if (department == null) {
			model.addAttribute("updatePos", properties.getProperty("deptIsNull"));
			log.info(properties.getProperty("deptIsNull"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetPosition
	 * @Description: 员工个人职位查询校验
	 * @param: @param posNum职位编号
	 * @param: @param model
	 * @param: @param request
	 * @return: boolean
	 */
	public boolean checkGetPosition(String posNum, Model model, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("employeeEmpId");
		String myPosNum = employee.getPosNum();
		if (StringUtils.isEmptyOrWhitespace(posNum)) {
			model.addAttribute("findMsg", properties.getProperty("posNumIsNull"));
			log.info(properties.getProperty("posNumIsNull"));
			return false;
		}
		if (!posNum.equals(myPosNum)) {
			model.addAttribute("findMsg", properties.getProperty("IllegalAccess"));
			log.info(properties.getProperty("IllegalAccess"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetPositionLike
	 * @Description: 职位模糊查询校验
	 * @param: @param id 输入的信息
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkGetPositionLike(String id, Model model) {
		if (StringUtils.isEmptyOrWhitespace(id)) {
			model.addAttribute("findMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		List<Position> positionList = positionService.findByEmpIdLike(id);
		if (positionList.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("posIsNull"));
			log.info(properties.getProperty("posIsNull"));
			return false;
		}
		model.addAttribute("Id", id);
		return true;
	}

}