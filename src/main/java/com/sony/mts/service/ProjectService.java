package com.sony.mts.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sony.mts.entity.Project;

/**
 * @ClassName: ProjectService
 * @Description: 项目Service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:32:44
 */
public interface ProjectService {
	/**
	 * @Title: insertProject
	 * @Description: 项目信息追加
	 * @param: @param project 项目对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insertProject(Project project);

	/**
	 * @Title: delProjectById
	 * @Description: 项目信息删除
	 * @param: @param proNum 项目编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int delProjectById(@Param("proNum") String proNum);

	/**
	 * @Title: updateProjectByNo
	 * @Description: 项目信息更新
	 * @param: @param project 项目对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateProjectByNo(Project project);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 项目信息取得
	 * @param: @param proNum 项目编号
	 * @param: @return 项目对象
	 * @return: Project
	 */
	Project findByPrimaryKey(String proNum);

	/**
	 * @Title: findByProName
	 * @Description: 项目信息取得
	 * @param: @param proName 项目名称
	 * @param: @return 项目对象
	 * @return: Project
	 */
	Project findByProName(@Param("proName") String proName);

	/**
	 * @Title: findAllProjects
	 * @Description: 全部项目信息取得
	 * @param: @return 项目List
	 * @return: List<Project> 返回类型
	 */
	List<Project> findAllProjects();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 项目信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 项目List
	 * @return: List<Project>
	 */
	List<Project> findByEmpIdLike(@Param("Id") String Id);

}
