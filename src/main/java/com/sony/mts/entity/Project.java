package com.sony.mts.entity;

/**
 * @ClassName: Project
 * @Description: 项目实体类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:30:23
 */
public class Project {

	/**
	 * @Fields proNum : 项目编号
	 */
	private String proNum;

	/**
	 * @Fields proName : 项目名称
	 */
	private String proName;

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

	/**
	 * @Title: getProName
	 * @Description: 获取项目名称
	 * @return: String
	 * @return: the proName 项目名称
	 */
	public String getProName() {
		return proName;
	}

	/**
	 * @Title: setProName
	 * @Description: 修改项目名称
	 * @param proName the proName to set 项目名称
	 * @return: String
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}

}