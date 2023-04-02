package com.sony.mts.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sony.mts.entity.Employee;


/**
 * @ClassName: EmployeeService
 * @Description: 员工Service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 12:31:57
 */
/**
 * @ClassName: EmployeeService
 * @Description: 员工service
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/10 14:33:47
 */

public interface EmployeeService {
	/**
	 * @Title: insertEmployee
	 * @Description: 员工信息追加
	 * @param: @param employee 员工对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insertEmployee(Employee employee);

	/**
	 * @Title: delEmployeerById
	 * @Description: 员工信息删除
	 * @param: @param empId 员工编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int delEmployeerById(@Param("empId") String empId);

	/**
	 * @Title: updateEmployeeByNo
	 * @Description: 员工信息更新
	 * @param: @param employee 员工对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateEmployeeByNo(Employee employee);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @return 员工对象
	 * @return: Employee
	 */
	Employee findByPrimaryKey(@Param("empId") String empId);

	/**
	 * @Title: findByDepNum
	 * @Description: 员工信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 员工List
	 * @return: List<Employee>
	 */
	List<Employee> findByDepNum(@Param("depNum") String depNum);

	/**
	 * @Title: findByPosNum
	 * @Description: 员工信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return 员工List
	 * @return: List<Employee>
	 */
	List<Employee> findByPosNum(@Param("posNum") String posNum);

	/**
	 * @Title: findByCardId
	 * @Description: 员工信息取得
	 * @param: @param cardId 身份证号
	 * @param: @return 员工对象
	 * @return: Employee
	 */
	Employee findByCardId(@Param("cardId") String cardId);

	/**
	 * @Title: findByMobileNum
	 * @Description: 员工信息取得
	 * @param: @param mobileNum 手机号
	 * @param: @return 员工对象
	 * @return: Employee
	 */
	Employee findByMobileNum(@Param("mobileNum") String mobileNum);

	/**
	 * @Title: findByEmailAdr
	 * @Description: 员工信息取得
	 * @param: @param emailAdr 邮箱地址
	 * @param: @return 员工对象
	 * @return: Employee
	 */
	Employee findByEmailAdr(@Param("emailAdr") String emailAdr);

	/**
	 * @Title: findByUser
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @param passWd 密码
	 * @param: @return 员工对象
	 * @return: Employee
	 */
	Employee findByUser(@Param("empId") String empId, @Param("passWd") String passWd);

	/**
	 * @Title: findAllEmployees
	 * @Description: 全部员工信息取得
	 * @param: @return 员工List
	 * @return: List<Employee>
	 */
	List<Employee> findAllEmployees();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 员工信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 员工List
	 * @return: List<Employee>
	 */
	List<Employee> findByEmpIdLike(@Param("Id") String Id);

	/**
	 * @Title: findEmpList
	 * @Description: 员工加职位信息查询
	 * @param: @return 员工List
	 * @return: List<Employee>
	 */
	List<Employee> findEmpList();

}
