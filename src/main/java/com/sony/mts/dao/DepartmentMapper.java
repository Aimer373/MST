package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Department;

/**
 * @ClassName: DepartmentMapper
 * @Description: 部门Mapper
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:23:31
 */
@Mapper
public interface DepartmentMapper {

	/**
	 * @Title: insert
	 * @Description: 部门信息追加
	 * @param: @param department 部门对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insert(Department department);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 部门信息删除
	 * @param: @param depNum 部门编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int deleteByPrimaryKey(String depNum);

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 部门信息更新
	 * @param: @param department 部门对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(Department department);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 部门信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 返回部门对象
	 * @return: Department
	 */
	Department findByPrimaryKey(String depNum);

	/**
	 * @Title: findByDepName
	 * @Description: 部门信息取得
	 * @param: @param depName 部门名称
	 * @param: @return 返回部门对象
	 * @return: Department
	 */
	Department findByDepName(String depName);

	/**
	 * @Title: findAllDepartments
	 * @Description: 所有部门信息取得
	 * @param: @return 返回部门集合
	 * @return: List<Department>
	 */
	List<Department> findAllDepartments();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 部门信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 返回部门集合
	 * @return: List<Department>
	 */
	List<Department> findByEmpIdLike(String Id);

}