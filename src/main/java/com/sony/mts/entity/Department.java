package com.sony.mts.entity;

/**
 * @ClassName: Department
 * @Description: 部门实体类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:29:01
 */
public class Department {

	/**
	 * @Fields depNum : 部门编号
	 */
	private String depNum;

	/**
	 * @Fields depName : 部门名称
	 */
	private String depName;

	/**
	 * @Fields chairmanNum : 负责人工号
	 */
	private String chairmanNum;

	/**
	 * @Title: getDepNum
	 * @Description: 获取部门编号
	 * @return: String
	 * @return: the depNum 部门编号
	 */
	public String getDepNum() {
		return depNum;
	}

	/**
	 * @Title: setDepNum
	 * @Description: 修改部门编号
	 * @param depNum the depNum to set 部门编号
	 * @return: String
	 */
	public void setDepNum(String depNum) {
		this.depNum = depNum;
	}

	/**
	 * @Title: getDepName
	 * @Description: 获取部门名称
	 * @return: String
	 * @return: the depName 部门名称
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * @Title: setDepName
	 * @Description: 修改部门名称
	 * @param depName the depName to set 部门名称
	 * @return: String
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * @Title: getChairmanNum
	 * @Description: 获取负责人工号
	 * @return: String
	 * @return: the chairmanNum 负责人工号
	 */
	public String getChairmanNum() {
		return chairmanNum;
	}

	/**
	 * @Title: setChairmanNum
	 * @Description: 修改负责人工号
	 * @param chairmanNum the chairmanNum to set 负责人工号
	 * @return: String
	 */
	public void setChairmanNum(String chairmanNum) {
		this.chairmanNum = chairmanNum;
	}

}