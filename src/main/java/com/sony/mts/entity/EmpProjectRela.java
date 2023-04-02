package com.sony.mts.entity;

/**
 * @ClassName: EmpProjectRela
 * @Description: 分配任务实体类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:29:53
 */
public class EmpProjectRela {

	/**
	 * @Fields taskId : 任务编号
	 */
	private String taskId;

	/**
	 * @Fields empId : 员工编号
	 */
	private String empId;

	/**
	 * @Fields proNum : 项目编号
	 */
	private String proNum;

	/**
	 * @Title: getTaskId
	 * @Description: 获取任务编号
	 * @return: String
	 * @return: the taskId 任务编号
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @Title: setTaskId
	 * @Description: 修改任务编号
	 * @param taskId the taskId to set 任务编号
	 * @return: String
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @Title: getEmpId
	 * @Description: 获取员工编号
	 * @return: String
	 * @return: the empId 员工编号
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @Title: setEmpId
	 * @Description: 修改员工编号
	 * @param empId the empId to set 员工编号
	 * @return: String
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @Title: getProNum
	 * @Description: 获取项目编号
	 * @return: String
	 * @return: the proNum 项目编号
	 */
	public String getProNum() {
		return proNum;
	}

	/**
	 * @Title: setProNum
	 * @Description: 修改项目编号
	 * @param proNum the proNum to set 项目编号
	 * @return: String
	 */
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

}