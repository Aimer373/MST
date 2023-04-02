package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.mts.dao.DepartmentMapper;
import com.sony.mts.entity.Department;
import com.sony.mts.service.DepartmentService;

/**
 * @ClassName: DepartmentServiceImpl
 * @Description: 部门Service实现类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:33:02
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	

	/**
	 * @Title: insertDepartment
	 * @Description: 部门信息追加
	 * @param: @param department 部门对象
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#insertDepartment(com.sony.mts.entity.Department)
	 */
	@Override
	public int insertDepartment(Department department) {
		return departmentMapper.insert(department);
	}

	/**
	 * @Title: delDepartmentById
	 * @Description: 部门信息删除
	 * @param: @param depNum 部门编号
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#delDepartmentById(java.lang.String)
	 */
	@Override
	public int delDepartmentById(String depNum) {
		return departmentMapper.deleteByPrimaryKey(depNum);
	}

	/**
	 * @Title: updateDepartmentByNo
	 * @Description: 部门信息更新
	 * @param: @param department 部门对象
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#updateDepartmentByNo(com.sony.mts.entity.Department)
	 */
	@Override
	public int updateDepartmentByNo(Department department) {
		return departmentMapper.updateByPrimaryKey(department);
	}

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 部门信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#findByPrimaryKey(java.lang.String)
	 */
	@Override
	public Department findByPrimaryKey(String depNum) {
		return departmentMapper.findByPrimaryKey(depNum);
	}

	/**
	 * @Title: findByDepName
	 * @Description: 部门信息取得
	 * @param: @param depName 部门名称
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#findByDepName(java.lang.String)
	 */
	@Override
	public Department findByDepName(String depName) {
		return departmentMapper.findByDepName(depName);
	}

	/**
	 * @Title: findAllDepartments
	 * @Description: 所有部门信息取得
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#findAllDepartments()
	 */
	@Override
	public List<Department> findAllDepartments() {
		return departmentMapper.findAllDepartments();
	}

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 部门信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return
	 * @see com.sony.mts.service.DepartmentService#findByEmpIdLike(java.lang.String)
	 */
	@Override
	public List<Department> findByEmpIdLike(String Id) {
		return departmentMapper.findByEmpIdLike(Id);
	}

}
