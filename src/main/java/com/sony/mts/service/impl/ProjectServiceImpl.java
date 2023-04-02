package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.mts.dao.ProjectMapper;
import com.sony.mts.entity.Project;
import com.sony.mts.service.ProjectService;

/**
 * @ClassName: ProjectServiceImpl
 * @Description: 项目Service实现类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:34:18
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectMapper projectMapper;

	/**
	 * @Title: insertProject
	 * @Description: 项目信息追加
	 * @param: @param project 项目对象
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#insertProject(com.sony.mts.entity.Project)
	 */
	@Override
	public int insertProject(Project project) {
		return projectMapper.insert(project);
	}

	/**
	 * @Title: delProjectById
	 * @Description: 项目信息删除
	 * @param: @param proNum 项目编号
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#delProjectById(java.lang.String)
	 */
	@Override
	public int delProjectById(String proNum) {
		return projectMapper.deleteByPrimaryKey(proNum);
	}

	/**
	 * @Title: updateProjectByNo
	 * @Description: 项目信息更新
	 * @param: @param project 项目对象
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#updateProjectByNo(com.sony.mts.entity.Project)
	 */
	@Override
	public int updateProjectByNo(Project project) {
		return projectMapper.updateByPrimaryKey(project);
	}

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 项目信息取得
	 * @param: @param proNum 项目编号
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#findByPrimaryKey(java.lang.String)
	 */
	@Override
	public Project findByPrimaryKey(String proNum) {
		return projectMapper.findByPrimaryKey(proNum);
	}

	/**
	 * @Title: findByProName
	 * @Description: 项目信息取得
	 * @param: @param proName 项目名称
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#findByProName(java.lang.String)
	 */
	@Override
	public Project findByProName(String proName) {
		return projectMapper.findByProName(proName);
	}

	/**
	 * @Title: findAllProjects
	 * @Description: 全部项目信息取得
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#findAllProjects()
	 */
	@Override
	public List<Project> findAllProjects() {
		return projectMapper.findAllProjects();
	}

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 项目信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return
	 * @see com.sony.mts.service.ProjectService#findByEmpIdLike(java.lang.String)
	 */
	@Override
	public List<Project> findByEmpIdLike(String Id) {
		return projectMapper.findByEmpIdLike(Id);
	}

}
