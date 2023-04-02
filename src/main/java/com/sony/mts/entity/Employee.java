package com.sony.mts.entity;

/**
 * @ClassName: Employee
 * @Description: 员工实体类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:29:18
 */
public class Employee {

	/**
	 * @Fields empId : 员工Id
	 */
	private String empId;

	/**
	 * @Fields depNum : 部门编号
	 */
	private String depNum;

	/**
	 * @Fields posNum : 职位编号
	 */
	private String posNum;

	/**
	 * @Fields empName : 员工姓名
	 */
	private String empName;

	/**
	 * @Fields cardId : 身份证号
	 */
	private String cardId;

	/**
	 * @Fields sex : 性别
	 */
	private String sex;

	/**
	 * @Fields mobileNum : 手机号码
	 */
	private String mobileNum;

	/**
	 * @Fields emailAdr : 邮件地址
	 */
	private String emailAdr;

	/**
	 * @Fields passWd : 密码
	 */
	private String passWd;

	/**
	 * @Fields position : 职位对象
	 */
	private Position position;

	/**
	 * @Fields department : 部门对象
	 */
	private Department department;

	/**
	 * @Title: Employee.java
	 * @Description: 员工对象构造函数（带参）
	 * @param: @param empId 员工编号
	 * @param: @param depNum 部门编号
	 * @param: @param posNum 职位编号
	 * @param: @param empName 员工姓名
	 * @param: @param cardId 身份证号
	 * @param: @param sex 性别
	 * @param: @param mobileNum 手机号
	 * @param: @param emailAdr 邮箱地址
	 * @param: @param passWd 密码
	 * @param: @param position 职位对象
	 * @param: @param department 部门对象
	 */
	public Employee(String empId, String depNum, String posNum, String empName, String cardId, String sex,
			String mobileNum, String emailAdr, String passWd, Position position, Department department) {
		super();
		this.empId = empId;
		this.depNum = depNum;
		this.posNum = posNum;
		this.empName = empName;
		this.cardId = cardId;
		this.sex = sex;
		this.mobileNum = mobileNum;
		this.emailAdr = emailAdr;
		this.passWd = passWd;
		this.position = position;
		this.department = department;
	}

	/**
	 * @Title: Employee.java
	 * @Description: 员工构造（无参）
	 */
	public Employee() {
		super();
	}

	/**
	 * @Title: getEmpId
	 * @Description: 获取部门编号
	 * @return: String
	 * @return: the empId 部门编号
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @Title: setEmpId
	 * @Description: 修改部门编号
	 * @param empId the empId to set 部门编号
	 * @return: String
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @Title: getDepNum
	 * @Description: 获取项目编号
	 * @return: String
	 * @return: the depNum 项目编号
	 */
	public String getDepNum() {
		return depNum;
	}

	/**
	 * @Title: setDepNum
	 * @Description: 修改项目编号
	 * @param depNum the depNum to set 项目编号
	 * @return: String
	 */
	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	/**
	 * @Title: getPosNum
	 * @Description: 获取职位编号
	 * @return: String
	 * @return: the posNum 职位编号
	 */
	public String getPosNum() {
		return posNum;
	}

	/**
	 * @Title: setPosNum
	 * @Description: 修改职位编号
	 * @param posNum the posNum to set 职位编号
	 * @return: String
	 */
	public void setPosNum(String posNum) {
		this.posNum = posNum;
	}

	/**
	 * @Title: getEmpName
	 * @Description: 获取员工姓名
	 * @return: String
	 * @return: the empName 员工姓名
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @Title: setEmpName
	 * @Description: 修改员工姓名
	 * @param empName the empName to set 员工姓名
	 * @return: String
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @Title: getCardId
	 * @Description: 获取身份证号
	 * @return: String
	 * @return: the cardId 身份证号
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * @Title: setCardId
	 * @Description: 修改身份证号
	 * @param cardId the cardId to set 身份证号
	 * @return: String
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * @Title: getSex
	 * @Description: 获取性别
	 * @return: String
	 * @return: the sex 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @Title: setSex
	 * @Description: 修改性别
	 * @param sex the sex to set 性别
	 * @return: String
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @Title: getMobileNum
	 * @Description: 获取手机号
	 * @return: String
	 * @return: the mobileNum 手机号
	 */
	public String getMobileNum() {
		return mobileNum;
	}

	/**
	 * @Title: setMobileNum
	 * @Description: 修改手机号
	 * @param mobileNum the mobileNum to set 手机号
	 * @return: String
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	/**
	 * @Title: getEmailAdr
	 * @Description: 获取邮箱地址
	 * @return: String
	 * @return: the emailAdr 邮箱地址
	 */
	public String getEmailAdr() {
		return emailAdr;
	}

	/**
	 * @Title: setEmailAdr
	 * @Description: 修改邮箱地址
	 * @param emailAdr the emailAdr to set 邮箱地址
	 * @return: String
	 */
	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}

	/**
	 * @Title: getPassWd
	 * @Description: 获取密码
	 * @return: String
	 * @return: the passWd 密码
	 */
	public String getPassWd() {
		return passWd;
	}

	/**
	 * @Title: setPassWd
	 * @Description: 修改密码
	 * @param passWd the passWd to set 密码
	 * @return: String
	 */
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	/**
	 * @Title: getPosition
	 * @Description: 获取职位对象
	 * @return: Position
	 * @return: the position 职位对象
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @Title: setPosition
	 * @Description: 修改职位对象
	 * @param position the position to set 职位对象
	 * @return: Position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @Title: getDepartment
	 * @Description: 获取部门对象
	 * @return: Department
	 * @return: the department 部门对象
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @Title: setDepartment
	 * @Description: 修改部门对象
	 * @param department the department to set 部门对象
	 * @return: Department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}