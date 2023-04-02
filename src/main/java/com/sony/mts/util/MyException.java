/**
 * @Title: MyException.java
 * @Package: com.sony.mts.util
 * @Description: 自定义异常
 * @author: 5109u12412宁誉程
 * @date: 2021/11/10 11:04:57
 * @Company: sony
 * @version: V1.0
 */
package com.sony.mts.util;

/**
 * @ClassName: MyException
 * @Description: 自定义异常
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/10 11:04:57
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields code : 状态码
	 */
	private int code;

	/**
	 * @Title: MyException.java
	 * @Description: 有参构造
	 * @param: @param code
	 * @param: @param msg
	 */
	public MyException(int code, String msg, String message) {
		super(message);
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @Title: getCode
	 * @Description: 获取状态码
	 * @return: int
	 * @return: the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @Title: setCode
	 * @Description: 修改状态码
	 * @param code the code to set
	 * @return: int
	 */
	public void setCode(int code) {
		this.code = code;
	}

	private String msg;

	/**
	 * @Title: getMsg
	 * @Description: 获取异常错误信息
	 * @return: String
	 * @return: the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @Title: setMsg
	 * @Description: 修改异常错误信息
	 * @param msg the msg to set
	 * @return: String
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @Title: MyException.java
	 * @Description: 无参构造
	 * @param:
	 */
	public MyException() {
		super();
	}
}
