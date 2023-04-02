/**
 * @Title: Flag.java
 * @Package: com.sony.mts.util
 * @Description: 标记
 * @author: 5109u12412宁誉程
 * @date: 2021/11/12 16:40:44
 * @Company: sony
 * @version: V1.0
 */
package com.sony.mts.util;

import org.springframework.ui.Model;

/**
 * @ClassName: Flag
 * @Description: 标记工具类
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/12 16:40:44
 */
public class Flag {
	/**
	 * @Title: checkFlag
	 * @Description: 判断是否显示改删按钮
	 * @param: @param flag 标记
	 * @param: @param model
	 * @return: void
	 */
	public void checkFlag(String flag, Model model) {
		if ("flag".equals(flag)) {
			model.addAttribute("flag", flag);
		} else {
			model.addAttribute("flag", null);
		}
	}
}
