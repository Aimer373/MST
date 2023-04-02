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
import com.sony.mts.entity.Project;
import com.sony.mts.service.EmpProjectRelalService;
import com.sony.mts.service.ProjectService;
import com.sony.mts.util.Access;
import com.sony.mts.util.CustomExtHandle;
import com.sony.mts.util.MyException;

/**
 * @ClassName: ProjectController
 * @Description: 项目Controller
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:23:11
 */
@Controller
@RequestMapping("/pro")
public class ProjectController extends CustomExtHandle {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmpProjectRelalService eprService;

	/**
	 * @Title: toAdd
	 * @Description: 项目信息追加页面跳转
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toAddPro")
	public String toAdd(Model model, HttpServletRequest request) {
		// 判断访问是否合法
		if (!new Access().accessNotWork(model, request)) {
			return new LoginController().logout(request);
		}
		return "/project/edit";
	}

	/**
	 * @Title: projectInsert
	 * @Description: 项目信息追加
	 * @param: @param project 项目对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/addPro")
	public String projectInsert(Project project, Model model, HttpServletRequest request) {
		log.info(properties.getProperty("addToProStart"));
		try {
			// 增加项目校验
			if (checkAddPro(project, model)) {
				projectService.insertProject(project);
				log.info(properties.getProperty("addToProSuccess"));
				// 返回分页后的项目一览页面
				return projectfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("addToProSuccess"), e);
			throw new MyException(409, properties.getProperty("addToProSuccess"), e.getMessage());
		}
		return "/project/edit";
	}

	/**
	 * @Title: projectDel
	 * @Description: 项目信息删除
	 * @param: @param proNum 项目编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping("/proDelete/{proNum}")
	public String projectDel(@PathVariable(value = "proNum") String proNum, Model model, HttpServletRequest request) {
		try {
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("deleteToProStart"));
			// 删除项目校验
			if (checkDeletePro(proNum, model)) {
				projectService.delProjectById(proNum);
				log.info(properties.getProperty("deleteToProSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("deleteToProFailed"), e);
			throw new MyException(409, properties.getProperty("deleteToProFailed"), e.getMessage());
		}
		// 返回分页后的项目一览页面
		return projectfindAll(model, request, 1, 5);
	}

	/**
	 * @Title: toUpdate
	 * @Description: 项目信息更新页面跳转
	 * @param: @param proNum 项目编号
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping("/toUpdatePro/{proNum}")
	public String toUpdate(@PathVariable(value = "proNum") String proNum, Model model, HttpServletRequest request) {
		Project project = projectService.findByPrimaryKey(proNum);
		// 判断访问是否合法
		if (!new Access().accessNotWork(model, request)) {
			return new LoginController().logout(request);
		}
		model.addAttribute("project", project);
		return "/project/edit";
	}

	/**
	 * @Title: projectUpdate
	 * @Description: 项目信息更新
	 * @param: @param project 项目对象
	 * @param: @param model
	 * @param: @param request
	 * @return: String
	 */
	@PostMapping("/updatePro")
	public String projectUpdate(Project project, Model model, HttpServletRequest request) {
		String proNum = project.getProNum();
		model.addAttribute("proNum", proNum);
		log.info(properties.getProperty("updateToProStart"));
		try {
			// 修改项目校验
			if (checkUpdatePro(project, model)) {
				projectService.updateProjectByNo(project);
				log.info(properties.getProperty("updateToProSuccess"));
				// 返回分页后的项目一览
				return projectfindAll(model, request, 1, 5);
			}
		} catch (Exception e) {
			log.error(properties.getProperty("updateToProFailed"), e);
			throw new MyException(409, properties.getProperty("updateToProFailed"), e.getMessage());
		}
		return "/project/edit";
	}

	/**
	 * @Title: projectfindAll
	 * @Description: 所有项目信息取得
	 * @param: @param model
	 * @param: @param request
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/findAll")
	public String projectfindAll(Model model, HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		try {
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("findAllProStart"));
			// 分页
			PageHelper.startPage(pageNum, pageSize);
			PageInfo<Project> pageInfo = new PageInfo<Project>(projectService.findAllProjects());
			model.addAttribute("pageInfo", pageInfo);
			log.info(properties.getProperty("findAllProSuccess"));
		} catch (Exception e) {
			log.error(properties.getProperty("findAllProFailed"), e);
			throw new MyException(409, properties.getProperty("findAllProFailed"), e.getMessage());
		}
		return "/project/project";
	}

	/**
	 * @Title: getProjectLike
	 * @Description: 项目信息模糊查询
	 * @param: @param request
	 * @param: @param model
	 * @param: @param pageNum 当前页
	 * @param: @param pageSize 每页显示条数
	 * @return: String
	 */
	@GetMapping("/likePro")
	public String getProjectLike(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		String id = request.getParameter("Id");
		try {
			// 判断访问是否合法
			if (!new Access().accessNotWork(model, request)) {
				return new LoginController().logout(request);
			}
			log.info(properties.getProperty("likeFindPosStart"));
			// 项目模糊查询校验
			if (checkGetProjectLike(id, model)) {
				// 分页
				PageHelper.startPage(pageNum, pageSize);
				PageInfo<Project> pageInfo = new PageInfo<Project>(projectService.findByEmpIdLike(id));
				model.addAttribute("pageInfo", pageInfo);
				log.info(properties.getProperty("likeFindProSuccess"));
			}
		} catch (Exception e) {
			log.error(properties.getProperty("likeFindProFailed"), e);
			throw new MyException(409, properties.getProperty("likeFindProFailed"), e.getMessage());
		}
		return "/project/project";
	}

	/**
	 * @Title: checkAddPro
	 * @Description: 增加项目校验
	 * @param: @param project 项目对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkAddPro(Project project, Model model) {
		String proNum = project.getProNum();
		String proName = project.getProName();
		Project pro = null;
		Project proByProName = null;
		pro = projectService.findByPrimaryKey(proNum);
		if (StringUtils.isEmptyOrWhitespace(proNum) || pro != null) {
			model.addAttribute("msg", properties.getProperty("proNumIsNullOrProExist"));
			log.info(properties.getProperty("proNumIsNullOrProExist"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(proName)) {
			model.addAttribute("msg", properties.getProperty("proNameIsNull"));
			log.info(properties.getProperty("proNameIsNull"));
			return false;
		}
		proByProName = projectService.findByProName(proName);
		if (proByProName != null) {
			model.addAttribute("msg", properties.getProperty("proExist"));
			log.info(properties.getProperty("proExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkDeletePro
	 * @Description: 删除项目校验
	 * @param: @param proNum项目编号
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkDeletePro(String proNum, Model model) {
		List<EmpProjectRela> eRelaList = eprService.findByProNum(proNum);
		if (eRelaList.size() != 0) {
			model.addAttribute("deletePro", properties.getProperty("projectHaveEmp"));
			log.info(properties.getProperty("projectHaveEmp"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkUpdatePro
	 * @Description: 修改项目校验
	 * @param: @param project 项目对象
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkUpdatePro(Project project, Model model) {
		String proNum = project.getProNum();
		String proName = project.getProName();
		Project proByProNum = null;
		Project proByProName = null;
		proByProNum = projectService.findByPrimaryKey(proNum);
		if (StringUtils.isEmptyOrWhitespace(proNum) || proByProNum == null) {
			model.addAttribute("updatePro", properties.getProperty("proNumIsNullOrProIsNull"));
			log.info(properties.getProperty("proNumIsNullOrProIsNull"));
			return false;
		}
		if (StringUtils.isEmptyOrWhitespace(proName)) {
			model.addAttribute("updatePro", properties.getProperty("proNameIsNull"));
			log.info(properties.getProperty("proNameIsNull"));
			return false;
		}
		proByProName = projectService.findByProName(proName);
		if (proByProName != null) {
			model.addAttribute("updatePro", properties.getProperty("proExist"));
			log.info(properties.getProperty("proExist"));
			return false;
		}
		return true;
	}

	/**
	 * @Title: checkGetProjectLike
	 * @Description:项目模糊查询校验
	 * @param: @param id 输入的信息
	 * @param: @param model
	 * @return: boolean
	 */
	public boolean checkGetProjectLike(String id, Model model) {
		if (StringUtils.isEmptyOrWhitespace(id)) {
			model.addAttribute("findMsg", properties.getProperty("likeMessageIsNull"));
			log.info(properties.getProperty("likeMessageIsNull"));
			return false;
		}
		List<Project> projectList = projectService.findByEmpIdLike(id);
		if (projectList.size() == 0) {
			model.addAttribute("findMsg", properties.getProperty("proIsNull"));
			log.info(properties.getProperty("proIsNull"));
			return false;
		}
		model.addAttribute("Id", id);
		return true;
	}

}