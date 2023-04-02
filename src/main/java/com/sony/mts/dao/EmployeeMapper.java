package com.sony.mts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sony.mts.entity.Employee;

/**
 * @ClassName: EmployeeMapper
 * @Description: 员工Mapper
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:27:27
 */
@Mapper
public interface EmployeeMapper {

	/**
	 * @Title: insert
	 * @Description: 员工信息追加
	 * @param: @param employee 员工对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int insert(Employee employee);

	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 员工信息删除
	 * @param: @param empId 员工编号
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int deleteByPrimaryKey(String empId);

	/**
	 * @Title: updateByPrimaryKey
	 * @Description: 员工信息更新
	 * @param: @param employee 员工对象
	 * @param: @return 成功返回1
	 * @return: int
	 */
	int updateByPrimaryKey(Employee employee);

	/**
	 * @Title: findByPrimaryKey
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @return 返回员工对象
	 * @return: Employee
	 */
	Employee findByPrimaryKey(String empId);

	/**
	 * @Title: findByDepNum
	 * @Description: 员工信息取得
	 * @param: @param depNum 部门编号
	 * @param: @return 返回员工集合
	 * @return: List<Employee>
	 */
	List<Employee> findByDepNum(String depNum);

	/**
	 * @Title: findByPosNum
	 * @Description: 员工信息取得
	 * @param: @param posNum 职位编号
	 * @param: @return 返回员工集合
	 * @return: List<Employee>
	 */
	List<Employee> findByPosNum(String posNum);

	/**
	 * @Title: findByCardId
	 * @Description: 员工信息取得
	 * @param: @param cardId 身份证号
	 * @param: @return 返回员工对象
	 * @return: Employee
	 */
	Employee findByCardId(String cardId);

	/**
	 * @Title: findByMobileNum
	 * @Description: 员工信息取得
	 * @param: @param mobileNum 手机号
	 * @param: @return 返回员工对象
	 * @return: Employee
	 */
	Employee findByMobileNum(String mobileNum);

	/**
	 * @Title: findByEmailAdr
	 * @Description: 员工信息取得
	 * @param: @param emailAdr 邮箱地址
	 * @param: @return 返回员工对象
	 * @return: Employee
	 */
	Employee findByEmailAdr(String emailAdr);

	/**
	 * @Title: findByUser
	 * @Description: 员工信息取得
	 * @param: @param empId 员工编号
	 * @param: @param passWd 密码
	 * @param: @return 返回员工对象
	 * @return: Employee
	 */
	Employee findByUser(String empId, String passWd);

	/**
	 * @Title: findAllEmployees
	 * @Description: 全部员工信息取得
	 * @param: @return 返回员工集合
	 * @return: List<Employee>
	 */
	List<Employee> findAllEmployees();

	/**
	 * @Title: findByEmpIdLike
	 * @Description: 员工信息模糊查询
	 * @param: @param Id 输入的信息
	 * @param: @return 返回员工集合
	 * @return: List<Employee>
	 */
	List<Employee> findByEmpIdLike(String Id);

	/**
	 * @Title: findEmpList
	 * @Description: 员工加职位信息查询
	 * @param: @return 返回员工集合
	 * @return: List<Employee>
	 */
	List<Employee> findEmpList();
}