package com.sony.mts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.mts.dao.EmployeeMapper;
import com.sony.mts.entity.Employee;
import com.sony.mts.service.EmployeeService;

/**
 * @ClassName: EmployeeServiceImpl
 * @Description: 员工Service实现类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:33:30
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;

	/**
	 * @Title: insertEmployee
	 * @Description: 员工信息追加
	 * @param: @param employee 员工对象
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#insertEmployee(com.sony.mts.entity.Employee)
	 */
	@Override
	public int insertEmployee(Employee employee) {
		return employeeMapper.insert(employee);
	}

	/**
	 * @Title: delEmployeerById
	 * @Description: 员工信息删除
	 * @param: @param empId 员工编号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#delEmployeerById(java.lang.String)
	 */
	@Override
	public int delEmployeerById(String empId) {
		return employeeMapper.deleteByPrimaryKey(empId);
	}

	/**
	 * @Title: updateEmployeeByNo
	 * @Description: 员工信息更新
	 * @param: @param employee 员工对象
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#updateEmployeeByNo(com.sony.mts.entity.Employee)
	 */
	@Override
	public int updateEmployeeByNo(Employee employee) {
		return employeeMapper.updateByPrimaryKey(employee);
	}

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByPrimaryKey(java.lang.String)
	 */
	@Override
	public Employee findByPrimaryKey(String empId) {
		return employeeMapper.findByPrimaryKey(empId);
	}

	/**
	 * @Title: findByDepNum
	 * @Description: 员工信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByDepNum(java.lang.String)
	 */
	@Override
	public List<Employee> findByDepNum(String depNum) {
		return employeeMapper.findByDepNum(depNum);
	}

	/**
	 * @Title: findByPosNum
	 * @Description: 员工信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByPosNum(java.lang.String)
	 */
	@Override
	public List<Employee> findByPosNum(String posNum) {
		return employeeMapper.findByPosNum(posNum);
	}

	/**
	 * @Title: findByCardId
	 * @Description: 员工信息取得
	 * @param: @param cardId 身份证号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByCardId(java.lang.String)
	 */
	@Override
	public Employee findByCardId(String cardId) {
		return employeeMapper.findByCardId(cardId);
	}

	/**
	 * @Title: findByMobileNum
	 * @Description: 员工信息取得
	 * @param: @param mobileNum 手机号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByMobileNum(java.lang.String)
	 */
	@Override
	public Employee findByMobileNum(String mobileNum) {
		return employeeMapper.findByMobileNum(mobileNum);
	}

	/**
	 * @Title: findByEmailAdr
	 * @Description: 员工信息取得
	 * @param: @param emailAdr 邮箱地址
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByEmailAdr(java.lang.String)
	 */
	@Override
	public Employee findByEmailAdr(String emailAdr) {
		return employeeMapper.findByEmailAdr(emailAdr);
	}

	/**
	 * @Title: findByUser
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @param passWd 密码
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByUser(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Employee findByUser(String empId, String passWd) {
		return employeeMapper.findByUser(empId, passWd);
	}

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 员工信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findByEmpIdLike(java.lang.String)
	 */
	@Override
	public List<Employee> findByEmpIdLike(String Id) {
		return employeeMapper.findByEmpIdLike(Id);
	}

	/**
	 * @Title: findAllEmployees
	 * @Description: 全部员工信息取得
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findAllEmployees()
	 */
	@Override
	public List<Employee> findAllEmployees() {
		return employeeMapper.findAllEmployees();
	}

	/**
	 * @Title: findEmpAndPos
	 * @Description: 员工信息集合取得
	 * @param: @param empId 员工编号
	 * @param: @return
	 * @see com.sony.mts.service.EmployeeService#findEmpAndPos(java.lang.String)
	 */
	@Override
	public List<Employee> findEmpList() {
		return employeeMapper.findEmpList();
	}

}
