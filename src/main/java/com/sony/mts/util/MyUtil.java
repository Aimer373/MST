/**
 * @Title: MyUtil.java
 * @Package: com.sony.mts.util
 * @Description: 描述该文件做什么
 * @author: 5109u12412宁誉程
 * @date: 2021/11/24 18:23:28
 * @Company: sony
 * @version: V1.0
 * @Copyright: 版权
 */
package com.sony.mts.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sony.mts.App;

/**
 * @ClassName: MyUtil
 * @Description: 提供其他类使用的log和properties
 * @author: 5109u12412宁誉程
 * @Company: sony
 * @date: 2021/11/24 18:23:28
 */
public class MyUtil {
	public static Logger log = LogManager.getLogger();

	public static Properties properties = new Properties();
	static {
		try {
			properties.load(new InputStreamReader(
					App.class.getClassLoader().getResourceAsStream("conf.properties"), "UTF-8"));
		} catch (IOException e) {
			log.error("读取失败", e);
		}
	}
}
