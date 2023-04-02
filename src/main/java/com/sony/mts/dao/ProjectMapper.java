package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Project;

/**
 * @ClassName: ProjectMapper
 * @Description: 项目Mapper
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:28:42
 */
@Mapper
public interface ProjectMapper {

	/**
	 * @Title: insert
	 * @Description: 项目信息追加
	 * @param: @param project 项目对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insert(Project project);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 项目信息删除
	 * @param: @param proNum 项目编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int deleteByPrimaryKey(String proNum);

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 项目信息更新
	 * @param: @param project 项目对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(Project project);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 项目信息取得
	 * @param: @param proNum 项目编号
	 * @param: @return 返回项目对象
	 * @return: Project
	 */
	Project findByPrimaryKey(String proNum);

	/**
	 * @Title: findByProName
	 * @Description: 项目信息取得
	 * @param: @param proName 项目名称
	 * @param: @return 返回项目对象
	 * @return: Project
	 */
	Project findByProName(String proName);

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 项目信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 返回项目集合
	 * @return: List<Project>
	 */
	List<Project> findByEmpIdLike(String Id);

	/**
	 * @Title: findAllProjects
	 * @Description: 全部项目信息取得
	 * @param: @return 返回项目集合
	 * @return: List<Project>
	 */
	List<Project> findAllProjects();

}