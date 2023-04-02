package com.sony.mts.entity;

/**
 * @ClassName: Position
 * @Description: 职位实体类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/02 11:30:06
 */
public class Position {

	/**
	 * @Fields posNum : 职位编号
	 */
	private String posNum;

	/**
	 * @Fields depNum : 部门编号
	 */
	private String depNum;

	/**
	 * @Fields posName : 职位名称
	 */
	private String posName;

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
	 * @Title: getPosName
	 * @Description: 获取部门名称
	 * @return: String
	 * @return: the posName 部门名称
	 */
	public String getPosName() {
		return posName;
	}

	/**
	 * @Title: setPosName
	 * @Description: 修改部门名称
	 * @param posName the posName to set 部门名称
	 * @return: String
	 */
	public void setPosName(String posName) {
		this.posName = posName;
	}

}