package com.sony.mts.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sony.mts.entity.Department;

/**
 * @ClassName: DepartmentService
 * @Description: 部门Service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:31:34
 */

public interface DepartmentService {
	/**
	 * @Title: insertDepartment
	 * @Description: 部门信息追加
	 * @param: @param department 部门对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insertDepartment(Department department);

	/**
	 * @Title: delDepartmentById
	 * @Description: 部门信息删除
	 * @param: @param depNum 部门编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int delDepartmentById(@Param("depNum") String depNum);

	/**
	 * @Title: updateDepartmentByNo
	 * @Description: 部门信息更新
	 * @param: @param department 部门对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateDepartmentByNo(Department department);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 部门信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 部门对象
	 * @return: Department
	 */
	Department findByPrimaryKey(String depNum);

	/**
	 * @Title: findByDepName
	 * @Description: 部门信息取得
	 * @param: @param depName 部门名称
	 * @param: @return 部门对象
	 * @return: Department
	 */
	Department findByDepName(@Param("depName") String depName);

	/**
	 * @Title: findAllDepartments
	 * @Description: 所有部门信息取得
	 * @param: @return 部门List
	 * @return: List<Department>
	 */
	List<Department> findAllDepartments();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 部门信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 部门List
	 * @return: List<Department>
	 */
	List<Department> findByEmpIdLike(@Param("Id") String Id);

}
